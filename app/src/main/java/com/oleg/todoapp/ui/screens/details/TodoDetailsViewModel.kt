package com.oleg.todoapp.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleg.todoapp.domain.usecase.GetTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TodoDetailsViewModel @Inject constructor(
    private val getTodo: GetTodoUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val todoId: Int = checkNotNull(savedStateHandle["todoId"]) {
        "Todo id is required"
    }

    private val _uiState = MutableStateFlow<TodoDetailsUiState>(TodoDetailsUiState.Loading)
    val uiState: StateFlow<TodoDetailsUiState> = _uiState.asStateFlow()

    init {
        loadTodo()
    }

    fun loadTodo() {
        _uiState.value = TodoDetailsUiState.Loading

        viewModelScope.launch {
            getTodo(todoId)
                .onSuccess { todo ->
                    _uiState.value = TodoDetailsUiState.Success(todo)
                }
                .onFailure { throwable ->
                    _uiState.value = TodoDetailsUiState.Error(
                        message = throwable.message ?: "Unable to load todo details.",
                    )
                }
        }
    }
}

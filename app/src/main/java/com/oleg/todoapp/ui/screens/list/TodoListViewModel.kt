package com.oleg.todoapp.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleg.todoapp.domain.usecase.GetTodosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getTodos: GetTodosUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoListUiState>(TodoListUiState.Loading)
    val uiState: StateFlow<TodoListUiState> = _uiState.asStateFlow()

    init {
        loadTodos()
    }

    fun loadTodos() {
        _uiState.value = TodoListUiState.Loading

        viewModelScope.launch {
            getTodos()
                .onSuccess { todos ->
                    _uiState.value = if (todos.isEmpty()) {
                        TodoListUiState.Empty
                    } else {
                        TodoListUiState.Success(todos)
                    }
                }
                .onFailure { throwable ->
                    _uiState.value = TodoListUiState.Error(
                        message = throwable.message ?: "Unable to load todo items.",
                    )
                }
        }
    }
}

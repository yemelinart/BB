package com.oleg.todoapp.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.oleg.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoDetailsViewModel(
    private val repository: TodoRepository,
    private val todoId: Int,
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoDetailsUiState>(TodoDetailsUiState.Loading)
    val uiState: StateFlow<TodoDetailsUiState> = _uiState.asStateFlow()

    init {
        loadTodo()
    }

    fun loadTodo() {
        _uiState.value = TodoDetailsUiState.Loading

        viewModelScope.launch {
            repository.getTodo(todoId)
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

    companion object {
        fun provideFactory(
            repository: TodoRepository,
            todoId: Int,
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TodoDetailsViewModel(repository, todoId) as T
                }
            }
    }
}

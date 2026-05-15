package com.oleg.todoapp.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.oleg.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val repository: TodoRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoListUiState>(TodoListUiState.Loading)
    val uiState: StateFlow<TodoListUiState> = _uiState.asStateFlow()

    init {
        loadTodos()
    }

    fun loadTodos() {
        _uiState.value = TodoListUiState.Loading

        viewModelScope.launch {
            repository.getTodos()
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

    companion object {
        fun provideFactory(repository: TodoRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TodoListViewModel(repository) as T
                }
            }
    }
}

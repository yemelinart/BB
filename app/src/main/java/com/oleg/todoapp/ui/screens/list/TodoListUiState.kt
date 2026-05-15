package com.oleg.todoapp.ui.screens.list

import com.oleg.todoapp.domain.model.Todo

sealed interface TodoListUiState {
    data object Loading : TodoListUiState
    data object Empty : TodoListUiState
    data class Success(val todos: List<Todo>) : TodoListUiState
    data class Error(val message: String) : TodoListUiState
}

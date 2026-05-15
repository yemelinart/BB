package com.oleg.todoapp.ui.screens.details

import com.oleg.todoapp.domain.model.Todo

sealed interface TodoDetailsUiState {
    data object Loading : TodoDetailsUiState
    data class Success(val todo: Todo) : TodoDetailsUiState
    data class Error(val message: String) : TodoDetailsUiState
}

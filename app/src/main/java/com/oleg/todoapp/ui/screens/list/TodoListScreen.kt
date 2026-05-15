package com.oleg.todoapp.ui.screens.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oleg.todoapp.ui.components.EmptyView
import com.oleg.todoapp.ui.components.ErrorView
import com.oleg.todoapp.ui.components.LoadingView
import com.oleg.todoapp.ui.components.TodoItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    uiState: TodoListUiState,
    onTodoClick: (Int) -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Todo Explorer")
                },
            )
        },
    ) { innerPadding ->
        when (uiState) {
            TodoListUiState.Loading -> LoadingView(
                modifier = Modifier.padding(innerPadding),
            )

            TodoListUiState.Empty -> EmptyView(
                message = "No todo items were returned.",
                modifier = Modifier.padding(innerPadding),
            )

            is TodoListUiState.Error -> ErrorView(
                message = uiState.message,
                onRetryClick = onRetryClick,
                modifier = Modifier.padding(innerPadding),
            )

            is TodoListUiState.Success -> TodoListContent(
                uiState = uiState,
                onTodoClick = onTodoClick,
                contentPadding = innerPadding,
            )
        }
    }
}

@Composable
private fun TodoListContent(
    uiState: TodoListUiState.Success,
    onTodoClick: (Int) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 16.dp,
            top = contentPadding.calculateTopPadding() + 8.dp,
            end = 16.dp,
            bottom = 16.dp,
        ),
    ) {
        items(
            items = uiState.todos,
            key = { todo -> todo.id },
        ) { todo ->
            TodoItemCard(
                todo = todo,
                onClick = { onTodoClick(todo.id) },
                modifier = Modifier.padding(vertical = 6.dp),
            )
        }
    }
}

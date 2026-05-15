package com.oleg.todoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oleg.todoapp.ui.screens.details.TodoDetailsScreen
import com.oleg.todoapp.ui.screens.details.TodoDetailsViewModel
import com.oleg.todoapp.ui.screens.list.TodoListScreen
import com.oleg.todoapp.ui.screens.list.TodoListViewModel

private sealed class Destination(val route: String) {
    data object TodoList : Destination("todos")
    data object TodoDetails : Destination("todos/{todoId}") {
        fun createRoute(todoId: Int): String = "todos/$todoId"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.TodoList.route,
    ) {
        composable(route = Destination.TodoList.route) {
            val viewModel: TodoListViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            TodoListScreen(
                uiState = uiState,
                onTodoClick = { todoId ->
                    navController.navigate(Destination.TodoDetails.createRoute(todoId))
                },
                onRetryClick = viewModel::loadTodos,
            )
        }

        composable(
            route = Destination.TodoDetails.route,
            arguments = listOf(
                navArgument("todoId") {
                    type = NavType.IntType
                },
            ),
        ) {
            val viewModel: TodoDetailsViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            TodoDetailsScreen(
                uiState = uiState,
                onBackClick = navController::navigateUp,
                onRetryClick = viewModel::loadTodo,
            )
        }
    }
}

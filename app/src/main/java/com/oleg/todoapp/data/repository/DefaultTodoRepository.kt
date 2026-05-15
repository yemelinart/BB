package com.oleg.todoapp.data.repository

import com.oleg.todoapp.data.model.toDomain
import com.oleg.todoapp.data.remote.TodoApiService
import com.oleg.todoapp.domain.model.Todo
import com.oleg.todoapp.domain.repository.TodoRepository

class DefaultTodoRepository(
    private val apiService: TodoApiService,
) : TodoRepository {

    override suspend fun getTodos(): Result<List<Todo>> = runCatching {
        apiService.getTodos().map { it.toDomain() }
    }

    override suspend fun getTodo(id: Int): Result<Todo> = runCatching {
        apiService.getTodo(id).toDomain()
    }
}

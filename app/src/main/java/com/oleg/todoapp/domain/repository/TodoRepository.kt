package com.oleg.todoapp.domain.repository

import com.oleg.todoapp.domain.model.Todo

interface TodoRepository {
    suspend fun getTodos(): Result<List<Todo>>
    suspend fun getTodo(id: Int): Result<Todo>
}

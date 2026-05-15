package com.oleg.todoapp.data.remote

import com.oleg.todoapp.data.model.TodoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<TodoDto>

    @GET("todos/{id}")
    suspend fun getTodo(@Path("id") id: Int): TodoDto
}

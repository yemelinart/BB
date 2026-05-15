package com.oleg.todoapp.di

import com.oleg.todoapp.data.remote.RetrofitClient
import com.oleg.todoapp.data.repository.DefaultTodoRepository
import com.oleg.todoapp.domain.repository.TodoRepository

interface AppContainer {
    val todoRepository: TodoRepository
}

class DefaultAppContainer : AppContainer {
    override val todoRepository: TodoRepository by lazy {
        DefaultTodoRepository(RetrofitClient.todoApiService)
    }
}

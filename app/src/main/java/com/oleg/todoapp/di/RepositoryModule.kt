package com.oleg.todoapp.di

import com.oleg.todoapp.data.repository.DefaultTodoRepository
import com.oleg.todoapp.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTodoRepository(repository: DefaultTodoRepository): TodoRepository
}

package com.oleg.todoapp.domain.usecase

import com.oleg.todoapp.domain.model.Todo
import com.oleg.todoapp.domain.repository.TodoRepository
import javax.inject.Inject

class GetTodoUseCase @Inject constructor(
    private val repository: TodoRepository,
) {
    suspend operator fun invoke(id: Int): Result<Todo> =
        repository.getTodo(id)
}

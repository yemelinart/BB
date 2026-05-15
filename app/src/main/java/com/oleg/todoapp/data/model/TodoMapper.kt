package com.oleg.todoapp.data.model

import com.oleg.todoapp.domain.model.Todo

fun TodoDto.toDomain(): Todo = Todo(
    userId = userId,
    id = id,
    title = title,
    completed = completed,
)

package com.eyedea.shared_sample.data.mapper

import com.eyedea.shared_sample.data.dto.TodoItemDto
import com.eyedea.shared_sample.domain.entity.TodoItemEntity

class TodoItemDtoMapper {
    operator fun invoke(from : TodoItemDto) : TodoItemEntity {
        return TodoItemEntity(
            completed = from.completed,
            id = from.id,
            title = from.title,
            userId = from.userId,
        )
    }
}
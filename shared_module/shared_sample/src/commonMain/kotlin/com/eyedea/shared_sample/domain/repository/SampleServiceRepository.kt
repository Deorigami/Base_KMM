package com.eyedea.shared_sample.domain.repository

import com.ardinata.shared_core.base.BaseRespondEntity
import com.eyedea.shared_sample.domain.entity.TodoItemEntity

interface SampleServiceRepository {
    suspend fun getTodoItem(id : Int) : BaseRespondEntity<TodoItemEntity>
}
package com.eyedea.shared_sample.data.repository

import com.ardinata.shared_core.base.BaseRespondEntity
import com.ardinata.shared_core.base.KtorNetworkProvider
import com.ardinata.shared_core.base.dtoToBaseRespondEntity
import com.eyedea.shared_sample.data.dto.TodoItemDto
import com.eyedea.shared_sample.domain.entity.TodoItemEntity
import com.eyedea.shared_sample.domain.repository.SampleServiceRepository
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent


class SampleServiceRepositoryImpl : SampleServiceRepository, KoinComponent {
    private val client get() = KtorNetworkProvider.client

    override suspend fun getTodoItem(id: Int): BaseRespondEntity<TodoItemEntity> {
        val respond = client.get("todos/$id").dtoToBaseRespondEntity<TodoItemDto,TodoItemEntity> { TodoItemEntity(
            completed = it.completed,
            id = it.id,
            title = it.title,
            userId = it.userId,
        ) }

        return respond
    }
}
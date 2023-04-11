package com.eyedea.shared_sample.domain.use_case

import com.ardinata.shared_core.base.BaseRespondEntity
import com.ardinata.shared_core.base.BaseUseCase
import com.eyedea.shared_sample.domain.entity.TodoItemEntity
import com.eyedea.shared_sample.domain.repository.SampleServiceRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class SampleSharedUseCase : BaseUseCase<Int, TodoItemEntity>(), KoinComponent {

    private val repo : SampleServiceRepository by inject()

    override val default: TodoItemEntity
        get() = TodoItemEntity.DEFAULT

    override suspend fun build(param: Int): BaseRespondEntity<TodoItemEntity> {
        return repo.getTodoItem(param)
    }
}
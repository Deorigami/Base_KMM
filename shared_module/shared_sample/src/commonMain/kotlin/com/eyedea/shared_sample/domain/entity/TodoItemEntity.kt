package com.eyedea.shared_sample.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class TodoItemEntity(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
) {
    companion object {
        val DEFAULT: TodoItemEntity = TodoItemEntity(completed = false, id = 0, title = "", userId = 0)
    }
}
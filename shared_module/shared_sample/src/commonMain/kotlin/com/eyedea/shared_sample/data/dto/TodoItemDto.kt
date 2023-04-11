package com.eyedea.shared_sample.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoItemDto(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)
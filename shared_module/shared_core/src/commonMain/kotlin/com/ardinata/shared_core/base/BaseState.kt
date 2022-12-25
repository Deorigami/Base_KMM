package com.ardinata.shared_core.base

sealed class BaseState<out R> {
    object Loading : BaseState<Nothing>()
    data class Success<R>(val data : R) : BaseState<R>()
    data class Error(val data: Throwable) : BaseState<Nothing>()
}
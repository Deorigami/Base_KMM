package com.ardinata.shared_core.base

data class BaseRespondEntity<R>(
    val data : R? = null,
    val error : Throwable? = null
)
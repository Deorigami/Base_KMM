package com.ardinata.shared_core.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseUseCase <P, R> {

    abstract val default : R
    abstract suspend fun build(param: P) : BaseRespondEntity<R>
    private var job : Job? = null

    fun execute(
        param: P,
        scope: CoroutineScope,
        onResult: (BaseState<R>) -> Unit
    ){
        job = scope.launch {
            onResult(either {
                build(param)
            })
        }
    }

    fun cancel() = job?.cancel()

    private suspend fun <V> either(block : suspend () -> BaseRespondEntity<V>) : BaseState<V> = kotlin.runCatching {
        val data = block.invoke().data
        if (data == null) BaseState.Error(Throwable("Error"))
        else BaseState.Success(data)
    }.getOrElse {
        BaseState.Error(it)
    }
}
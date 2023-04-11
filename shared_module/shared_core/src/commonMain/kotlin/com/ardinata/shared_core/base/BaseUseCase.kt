package com.ardinata.shared_core.base

import com.ardinata.shared_core.extensions.tryDeserialize
import io.ktor.client.statement.*
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

suspend inline fun <reified DTO, ENTITY> HttpResponse.dtoToBaseRespondEntity(block : (DTO) -> ENTITY) : BaseRespondEntity<ENTITY> {
    val dto = tryDeserialize<DTO>(this.bodyAsText()){
        return BaseRespondEntity(error = Throwable(it))
    } ?: return BaseRespondEntity(error = Throwable("SOMETHING WENT WRONG"))
    return BaseRespondEntity(block.invoke(dto))
}

suspend inline fun <reified DTO, ENTITY> HttpResponse.toBaseRespondEntity(block : (DTO) -> ENTITY) : BaseRespondEntity<ENTITY> {
    val dto = tryDeserialize<BaseRespondDto<DTO>>(this.bodyAsText()){
        return BaseRespondEntity(error = Throwable(it))
    } ?: return BaseRespondEntity(error = Throwable("SOMETHING WENT WRONG"))
    return BaseRespondEntity(dto.data?.let { block.invoke(it) })
}
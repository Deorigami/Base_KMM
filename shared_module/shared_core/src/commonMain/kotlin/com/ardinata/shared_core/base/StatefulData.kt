package com.ardinata.shared_core.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StatefulData<P, R>(
    private val useCase: BaseUseCase<P, R>,
    private val scope: CoroutineScope
) {
    private val _state = MutableStateFlow<StateData<R>>(
        StateData(
            data = null,
            isLoading = true,
            errorData = null
        )
    )
    private val isLoading = MutableStateFlow(false)
    val state : CommonFlow<StateData<R>> get() = _state.asCommonFlow()

    fun listen(
        onSuccess : (R) -> Unit = {},
        onStart : () -> Unit = {},
        onError : (Throwable) -> Unit = {},
        onComplete : () -> Unit = {},
    ){
        isLoading.onEach {
            if (it) onStart.invoke()
        }
        state.onEach {
            when {
                it.data != null -> onSuccess.invoke(it.data)
                it.errorData != null -> onError.invoke(it.errorData)
                else -> {}
            }
        }.onStart {
            onStart.invoke()
        }.onCompletion {
            onComplete.invoke()
            isLoading.value = false
            _state.value = _state.value.copy(
                isLoading = false
            )
        }.launchIn(scope)
    }

    fun loadData(param: P){
        isLoading.value = true
        _state.value = StateData(
            data = null,
            isLoading = true,
            errorData = null
        )
        scope.launch {
            useCase.execute(
                param,
                scope,
            ){
                when(it){
                    is BaseState.Error -> _state.value = StateData(
                        data = null,
                        isLoading = false,
                        errorData = it.data
                    )
                    is BaseState.Success -> _state.value = StateData(
                        data = it.data,
                        isLoading = false,
                        errorData = null
                    )
                    else -> {}
                }
            }
        }
    }
}

data class StateData<R>(
    val data : R?,
    val isLoading : Boolean = false,
    val errorData : Throwable?
)
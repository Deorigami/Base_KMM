package com.ardinata.shared_core.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class BaseViewModel {
    actual val scope : CoroutineScope = MainScope()
    protected actual fun onCleared() {

    }

    fun clear(){
        onCleared()
        scope.cancel()
    }
}
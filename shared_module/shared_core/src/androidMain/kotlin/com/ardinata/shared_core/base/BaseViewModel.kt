package com.ardinata.shared_core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual abstract class BaseViewModel : ViewModel() {
    actual val scope: CoroutineScope
        get() = viewModelScope

    actual override fun onCleared() {
        super.onCleared()
    }
}
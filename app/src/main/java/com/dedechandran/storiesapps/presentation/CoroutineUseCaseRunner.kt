package com.dedechandran.storiesapps.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface CoroutineUseCaseRunner {
    val coroutineScope: CoroutineScope

    fun <T> withUseCaseScope(
        onLoading: ((Boolean) -> Unit)? = null,
        onError: ((String) -> Unit)? = null,
        onSuccess: (T) -> Unit,
        block: (suspend () -> Flow<T>)
    ){
        coroutineScope.launch {
            block.invoke()
                .onStart { onLoading?.invoke(true) }
                .onEach { onSuccess.invoke(it) }
                .catch { onError?.invoke("Unknown Error") }
                .launchIn(coroutineScope)
        }
    }
}
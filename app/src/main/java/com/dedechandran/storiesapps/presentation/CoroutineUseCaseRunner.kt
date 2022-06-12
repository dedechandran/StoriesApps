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
        block: (suspend () -> Flow<Result<T>>)
    ){
        coroutineScope.launch {
            block.invoke()
                .onStart { onLoading?.invoke(true) }
                .onEach { result ->
                    if (result.isSuccess) {
                        result.getOrNull()?.let {
                            onSuccess.invoke(it)
                        }
                    }else {
                        result.exceptionOrNull()?.let {
                            onError?.invoke(it.message ?: "Unknown Error")
                        }
                    }
                }
                .catch { onError?.invoke(it.message ?: "Unknown Error") }
                .onCompletion { onLoading?.invoke(false) }
                .launchIn(coroutineScope)
        }
    }
}
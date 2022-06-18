package com.dedechandran.storiesapps.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface CoroutineUseCaseRunner {
    val coroutineScope: CoroutineScope

    fun <T> withUseCaseScope(
        onStart: (() -> Unit)? = null,
        onCompletion: (() -> Unit)? = null,
        onError: ((String?) -> Unit)? = null,
        onSuccess: (T?) -> Unit,
        block: (suspend () -> Flow<Result<T?>>)
    ){
        coroutineScope.launch {
            block.invoke()
                .onStart { onStart?.invoke() }
                .onEach { result ->
                    if (result.isSuccess) {
                        onSuccess.invoke(result.getOrNull())
                    }else {
                        result.exceptionOrNull()?.let {
                            onError?.invoke(it.message ?: "Unknown Error")
                        }
                    }
                }
                .catch { onError?.invoke(it.message ?: "Unknown Error") }
                .onCompletion { onCompletion?.invoke() }
                .launchIn(coroutineScope)
        }
    }
}
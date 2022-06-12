package com.dedechandran.storiesapps.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedechandran.storiesapps.common.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

open class BaseViewModel : ViewModel(), CoroutineUseCaseRunner {
    override val coroutineScope: CoroutineScope
        get() = viewModelScope

    val isLoadingEvent = SingleLiveEvent<Boolean>()
    val showErrorEvent = SingleLiveEvent<String>()

    override fun <T> withUseCaseScope(
        onLoading: ((Boolean) -> Unit)?,
        onError: ((String) -> Unit)?,
        onSuccess: (T) -> Unit,
        block: suspend () -> Flow<Result<T>>
    ) {
        val loadingUpdater = { isLoading: Boolean ->
            isLoadingEvent.setValue(isLoading)
        }
        val errorHandler = { errorMessage: String ->
            showErrorEvent.setValue(errorMessage)
        }
        super.withUseCaseScope(
            onLoading = {
                onLoading?.invoke(it) ?: loadingUpdater.invoke(it)
            },
            onError = {
                onError?.invoke(it) ?: errorHandler.invoke(it)
            },
            onSuccess,
            block
        )
    }
}
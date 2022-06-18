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
    val showErrorEvent = SingleLiveEvent<String?>()

}
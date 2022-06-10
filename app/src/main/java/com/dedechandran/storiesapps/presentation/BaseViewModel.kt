package com.dedechandran.storiesapps.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

open class BaseViewModel: ViewModel(), CoroutineUseCaseRunner {
    override val coroutineScope: CoroutineScope
        get() = viewModelScope
}
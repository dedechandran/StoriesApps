package com.dedechandran.storiesapps.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

data class TextInputState(
    val text: MutableLiveData<String> = MutableLiveData<String>(),
    val errorMessage: LiveData<String?> = Transformations.map(text) {
        onValidateValue?.invoke(it)
    },
    val onValidateValue: ((String) -> String?)? = null
)

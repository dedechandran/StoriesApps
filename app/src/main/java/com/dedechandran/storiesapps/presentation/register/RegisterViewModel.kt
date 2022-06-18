package com.dedechandran.storiesapps.presentation.register

import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import com.dedechandran.storiesapps.common.SingleLiveEvent
import com.dedechandran.storiesapps.domain.RegisterUseCase
import com.dedechandran.storiesapps.presentation.BaseViewModel
import com.dedechandran.storiesapps.presentation.TextInputState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase): BaseViewModel() {

    val name = TextInputState() {
        if(it.isEmpty()) {
            "Name is required"
        }else {
            null
        }
    }

    val email = TextInputState() {
        if(!Patterns.EMAIL_ADDRESS.matcher(it).matches()) {
            "Invalid email"
        }else {
            null
        }
    }

    val password = TextInputState() {
        if(it.length < 6) {
            "Password is less than 6 character"
        }else {
            null
        }
    }

    val isBtnRegisterEnabled = MediatorLiveData<Boolean>().apply {
        addSource(name.errorMessage) {
            value = it.isNullOrEmpty() && email.errorMessage.value.isNullOrEmpty() && password.errorMessage.value.isNullOrEmpty()
        }
        addSource(email.errorMessage) {
            value = it.isNullOrEmpty() && name.errorMessage.value.isNullOrEmpty() && password.errorMessage.value.isNullOrEmpty()
        }
        addSource(password.errorMessage) {
            value = it.isNullOrEmpty() && name.errorMessage.value.isNullOrEmpty() && email.errorMessage.value.isNullOrEmpty()
        }
    }

    val successfullyRegisterEvent = SingleLiveEvent<Unit>()

    fun register() {
        withUseCaseScope(
            onStart = {
                isLoadingEvent.setValue(true)
            },
            onCompletion = {
                isLoadingEvent.setValue(false)
            },
            onError = {
                showErrorEvent.setValue(it)
            },
            onSuccess = {
                successfullyRegisterEvent.setValue(Unit)
            }
        ) {
            val params = RegisterUseCase.Params(
                name = name.text.value.orEmpty(),
                email = email.text.value.orEmpty(),
                password = password.text.value.orEmpty()
            )
            registerUseCase.invoke(params)
        }
    }

}
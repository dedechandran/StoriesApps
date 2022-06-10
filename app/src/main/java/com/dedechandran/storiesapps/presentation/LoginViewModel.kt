package com.dedechandran.storiesapps.presentation

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dedechandran.storiesapps.common.SingleLiveEvent
import com.dedechandran.storiesapps.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val isLoginBtnEnabled = MediatorLiveData<Boolean>().apply {
        addSource(email) { email ->
            value = email.isNotEmpty() && password.value.orEmpty().isNotEmpty()
        }
        addSource(password) { password ->
            value = email.value.orEmpty().isNotEmpty() && password.isNotEmpty()
        }
    }

    val showSnackBarEvent = SingleLiveEvent<String>()

    fun login() {
        withUseCaseScope(
            onLoading = {
                showSnackBarEvent.setValue("Loading")
            },
            onError = {
                showSnackBarEvent.setValue(it)
            },
            onSuccess = {
                showSnackBarEvent.setValue("Success")
            },
        ) {
            val params = LoginUseCase.Params(
                email = email.value.toString(),
                password = password.value.toString()
            )
            loginUseCase.invoke(params)
        }
    }
}
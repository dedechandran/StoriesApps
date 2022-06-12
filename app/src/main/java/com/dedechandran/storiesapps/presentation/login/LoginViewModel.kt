package com.dedechandran.storiesapps.presentation.login

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import com.dedechandran.storiesapps.domain.LoginUseCase
import com.dedechandran.storiesapps.presentation.BaseViewModel
import com.dedechandran.storiesapps.presentation.TextInputState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel() {

    val email = TextInputState()
    val password = TextInputState()

    val isLoginBtnEnabled = MediatorLiveData<Boolean>().apply {
        addSource(email.text) { email ->
            value = email.isNotEmpty() && password.text.value.orEmpty().isNotEmpty()
        }
        addSource(password.text) { password ->
            value = email.text.value.orEmpty().isNotEmpty() && password.isNotEmpty()
        }
    }

    fun login() {
        withUseCaseScope(
            onSuccess = {
                Log.d("onSuccessLogin", "${it}")
            },
        ) {
            val params = LoginUseCase.Params(
                email = email.text.value.toString(),
                password = password.text.value.toString()
            )
            loginUseCase.invoke(params)
        }
    }
}
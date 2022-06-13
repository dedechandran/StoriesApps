package com.dedechandran.storiesapps.presentation.splash

import com.dedechandran.storiesapps.common.SingleLiveEvent
import com.dedechandran.storiesapps.domain.GetLoginSessionUseCase
import com.dedechandran.storiesapps.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getLoginSessionUseCase: GetLoginSessionUseCase) : BaseViewModel() {



    val isLoggedIn = SingleLiveEvent<Boolean>()

    fun initialized() {
        withUseCaseScope(
            onSuccess = { loginSession ->
                isLoggedIn.setValue(loginSession != null)
            }
        ){
            delay(1000)
            getLoginSessionUseCase.invoke(GetLoginSessionUseCase.Params)
        }
    }
}
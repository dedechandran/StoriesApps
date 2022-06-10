package com.dedechandran.storiesapps.presentation.register

import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dedechandran.storiesapps.domain.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase): ViewModel() {

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val nameErrorMessage = Transformations.map(name) {
        if(it.isNullOrEmpty()) {
            "Name is required"
        }else {
            null
        }
    }

    val emailErrorMessage = Transformations.map(email) {
        if(!Patterns.EMAIL_ADDRESS.matcher(it).matches()) {
            "Invalid email"
        }else {
            null
        }
    }

    val passwordErrorMessage = Transformations.map(password) {
        if(it.length < 6) {
            "Password is less than 6 character"
        }else {
            null
        }
    }

    val isBtnRegisterEnabled = MediatorLiveData<Boolean>().apply {
        addSource(nameErrorMessage) {
            value = it.isNullOrEmpty().not() && emailErrorMessage.value.isNullOrEmpty().not() && passwordErrorMessage.value.isNullOrEmpty().not()
        }
        addSource(emailErrorMessage) {
            value = it.isNullOrEmpty().not() && nameErrorMessage.value.isNullOrEmpty().not() && passwordErrorMessage.value.isNullOrEmpty().not()
        }
        addSource(passwordErrorMessage) {
            value = it.isNullOrEmpty().not() && nameErrorMessage.value.isNullOrEmpty().not() && emailErrorMessage.value.isNullOrEmpty().not()
        }
    }

    fun register() {

    }

}
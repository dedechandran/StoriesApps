package com.dedechandran.storiesapps.presentation

sealed class LoginEvent {
    data class EnteredEmail(val value: String): LoginEvent()
    data class EnteredPassword(val value: String): LoginEvent()

    object Login: LoginEvent()
}

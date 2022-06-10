package com.dedechandran.storiesapps.data.local

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun saveLoginSession(loginEntity: LoginEntity) {
        with(sharedPreferences.edit()) {
            putString(LOGIN_SESSION_KEY, Gson().toJson(loginEntity))
            apply()
        }
    }

    fun getLoginSession(): LoginEntity? {
        val loginSession = sharedPreferences.getString(LOGIN_SESSION_KEY, "")
        return if (loginSession == null) {
            null
        } else {
            Gson().fromJson(
                loginSession,
                LoginEntity::class.java
            )
        }
    }

    companion object {
        private const val LOGIN_SESSION_KEY = "LOGIN_SESSION_KEY"
    }
}
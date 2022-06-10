package com.dedechandran.storiesapps.data.network

import com.dedechandran.storiesapps.domain.LoginModel
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("loginResult")
    var data: LoginResult? = null
) {
    data class LoginResult(
        @SerializedName("userId")
        val userId: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("token")
        val token: String
    )
}


fun LoginResponse.toLoginModel() = LoginModel(
    name = data?.name.orEmpty(),
    userId = data?.userId.orEmpty(),
    token = data?.token.orEmpty()
)

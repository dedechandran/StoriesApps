package com.dedechandran.storiesapps.data.local

import com.dedechandran.storiesapps.domain.LoginModel

data class LoginEntity(
    val name: String,
    val userId: String,
    val token: String
)

fun LoginEntity.toLoginModel() = LoginModel(
    name = name,
    userId = userId,
    token = token
)

package com.dedechandran.storiesapps.domain

import com.dedechandran.storiesapps.data.local.LoginEntity

data class LoginModel(
    val userId: String,
    val name: String,
    val token: String
)

fun LoginModel.toLoginEntity() = LoginEntity(
    userId = userId,
    name = name,
    token = token
)

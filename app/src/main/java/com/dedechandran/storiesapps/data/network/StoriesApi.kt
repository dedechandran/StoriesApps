package com.dedechandran.storiesapps.data.network

import com.dedechandran.storiesapps.common.GenericResponse
import com.dedechandran.storiesapps.common.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface StoriesApi {

    @POST("login")
    suspend fun login(@Body body: LoginRequest): GenericResponse<LoginResponse>

    @POST("register")
    suspend fun register(@Body body: RegisterRequest): GenericResponse<Unit>
}
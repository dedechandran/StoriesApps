package com.dedechandran.storiesapps.domain


import kotlinx.coroutines.flow.Flow

interface StoriesRepository {
    suspend fun login(email: String, password: String): Flow<Result<LoginModel>>
    suspend fun register(name: String, email: String, password: String): Flow<Result<Unit>>
    suspend fun saveLoginSession(loginSession: LoginModel): Flow<Result<Unit>>
}
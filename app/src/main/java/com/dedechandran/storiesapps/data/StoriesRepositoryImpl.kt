package com.dedechandran.storiesapps.data

import android.util.Log
import com.dedechandran.storiesapps.common.*
import com.dedechandran.storiesapps.data.local.SessionManager
import com.dedechandran.storiesapps.data.network.LoginRequest
import com.dedechandran.storiesapps.data.network.RegisterRequest
import com.dedechandran.storiesapps.data.network.StoriesApi
import com.dedechandran.storiesapps.data.network.toLoginModel
import com.dedechandran.storiesapps.domain.LoginModel
import com.dedechandran.storiesapps.domain.StoriesRepository
import com.dedechandran.storiesapps.domain.toLoginEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Result

class StoriesRepositoryImpl @Inject constructor(
    private val storiesApi: StoriesApi,
    private val sessionManager: SessionManager
) : StoriesRepository {

    override suspend fun login(email: String, password: String): Flow<Result<LoginModel>> {
        return flow {
            val request = LoginRequest(
                email = email,
                password = password
            )
            when (val result = storiesApi.login(request)) {
                is NetworkResponse.Success -> {
                    val data = result.body.toLoginModel()
                    emit(Result.success(data))
                }

                is NetworkResponse.ApiError -> {
                    when (result.code) {
                        400 -> emit(Result.failure(StoriesException.BadRequestException))
                        401 -> emit(Result.failure(StoriesException.UserUnauthorizedException))
                        404 -> emit(Result.failure(StoriesException.UserNotFoundException))
                    }
                }

                is NetworkResponse.NetworkError -> {
                    emit(Result.failure(StoriesException.NoNetworkException))
                }

                is NetworkResponse.UnknownError -> {
                    emit(Result.failure(StoriesException.UnknownErrorException))
                }
            }
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Flow<Result<Unit>> {
        return flow {
            val request = RegisterRequest(
                name = name,
                email = email,
                password = password
            )
            when (val result = storiesApi.register(request)) {
                is NetworkResponse.Success -> {
                    emit(Result.success(Unit))
                }

                is NetworkResponse.ApiError -> {
                    when (result.code) {
                        400 -> emit(Result.failure(StoriesException.BadRequestException))
                    }
                }

                is NetworkResponse.NetworkError -> {
                    emit(Result.failure(StoriesException.NoNetworkException))
                }

                is NetworkResponse.UnknownError -> {
                    emit(Result.failure(StoriesException.UnknownErrorException))
                }
            }
        }
    }

    override suspend fun saveLoginSession(loginSession: LoginModel): Flow<Result<Unit>> {
        return flow {
            runCatching {
                sessionManager.saveLoginSession(loginSession.toLoginEntity())
            }
        }
    }
}
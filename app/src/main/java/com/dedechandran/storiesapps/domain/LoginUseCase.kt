package com.dedechandran.storiesapps.domain

import android.util.Log
import com.dedechandran.storiesapps.common.StoriesException
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: StoriesRepository) :
    BaseUseCase<LoginUseCase.Params, Result<Unit>>() {

    override suspend fun buildUseCase(params: Params): Flow<Result<Unit>> {
        return repository.login(
            email = params.email,
            password = params.password
        ).flatMapConcat { result ->
            result.fold(
                onSuccess = {
                    repository.saveLoginSession(it)
                },
                onFailure = {
                   flowOf(Result.failure(it))
                }
            )
        }
    }

    data class Params(
        val email: String,
        val password: String
    )
}
package com.dedechandran.storiesapps.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: StoriesRepository) :
    BaseUseCase<RegisterUseCase.Params, Result<Unit>>() {

    override suspend fun buildUseCase(params: Params): Flow<Result<Unit>> {
        return repository.register(
            name = params.name,
            email = params.email,
            password = params.password
        )
    }

    data class Params(
        val name: String,
        val email: String,
        val password: String
    )
}
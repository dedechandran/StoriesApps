package com.dedechandran.storiesapps.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLoginSessionUseCase @Inject constructor(private val repository: StoriesRepository): BaseUseCase<GetLoginSessionUseCase.Params, Result<LoginModel?>>() {
    override suspend fun buildUseCase(params: Params): Flow<Result<LoginModel?>> {
        return repository.getLoginSession()
    }

    object Params
}
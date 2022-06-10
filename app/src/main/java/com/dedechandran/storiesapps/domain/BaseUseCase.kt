package com.dedechandran.storiesapps.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCase<ParamType, ReturnType> {

    open val dispatcher: CoroutineDispatcher = Dispatchers.IO

    abstract suspend fun buildUseCase(params: ParamType): Flow<ReturnType>

    suspend operator fun invoke(params: ParamType) = buildUseCase(params)
        .flowOn(dispatcher)
}
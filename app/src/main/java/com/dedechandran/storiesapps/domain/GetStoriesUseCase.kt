package com.dedechandran.storiesapps.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoriesUseCase @Inject constructor(private val repository: StoriesRepository) :
    BaseUseCase<GetStoriesUseCase.Params, Result<List<StoryModel>>>() {

    override suspend fun buildUseCase(params: Params): Flow<Result<List<StoryModel>>> {
        return repository.getStories()
    }

    object Params
}
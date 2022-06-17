package com.dedechandran.storiesapps.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dedechandran.storiesapps.domain.GetStoriesUseCase
import com.dedechandran.storiesapps.domain.StoryModel
import com.dedechandran.storiesapps.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getStoriesUseCase: GetStoriesUseCase) :
    BaseViewModel() {

    private val stories = MutableLiveData<List<StoryModel>>()
    val storyDisplayedItems = Transformations.map(stories,this::getStoryDisplayedItems)

    fun getStories() {
        withUseCaseScope(
            onSuccess = {
                stories.value = it
            }
        ) {
            getStoriesUseCase.invoke(GetStoriesUseCase.Params)
        }
    }

    private fun getStoryDisplayedItems(stories: List<StoryModel>) = stories.map {
        StoryItem(
            id = it.id,
            dateTime = it.createdAt,
            description = it.description,
            image = it.photoUrl
        )
    }

}
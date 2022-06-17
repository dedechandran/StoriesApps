package com.dedechandran.storiesapps.data.network

import com.dedechandran.storiesapps.domain.StoryModel
import com.google.gson.annotations.SerializedName

data class StoryResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("listStory")
    val stories: List<StoryItem>
){
    data class StoryItem(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("photoUrl")
        val photoUrl: String,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("lat")
        val lat: Double,
        @SerializedName("lon")
        val lon: Double
    )
}

fun StoryResponse.toStoryModel() = stories.map {
    StoryModel(
        id = it.id,
        name = it.name,
        description = it.description,
        photoUrl = it.photoUrl,
        createdAt = it.createdAt,
        lat = it.lat,
        lon = it.lon
    )
}
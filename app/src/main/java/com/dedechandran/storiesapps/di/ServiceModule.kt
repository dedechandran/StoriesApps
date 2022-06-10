package com.dedechandran.storiesapps.di

import com.dedechandran.storiesapps.data.network.StoriesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideStoriesApi(retrofit: Retrofit): StoriesApi {
        return retrofit.create(StoriesApi::class.java)
    }
}
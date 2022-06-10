package com.dedechandran.storiesapps.di

import com.dedechandran.storiesapps.data.StoriesRepositoryImpl
import com.dedechandran.storiesapps.domain.StoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindStoriesRepository(storiesRepositoryImpl: StoriesRepositoryImpl): StoriesRepository

}
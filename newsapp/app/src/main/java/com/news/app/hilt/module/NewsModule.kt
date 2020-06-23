package com.news.app.hilt.module

import com.news.news.api.NewsApi
import com.news.news.data.NewsApiImpl
import com.news.news.presentation.NewsRepository
import com.news.news.presentation.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NewsModule {
    @Binds
    abstract fun bindsNewsApi(impl: NewsApiImpl): NewsApi

    @Binds
    abstract fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}
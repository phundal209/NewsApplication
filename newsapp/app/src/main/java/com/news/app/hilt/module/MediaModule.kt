package com.news.app.hilt.module

import com.news.media.api.ImageProvider
import com.news.media.data.ImageProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class MediaModule {
    @Binds
    @Singleton
    abstract fun bindsImageProvider(impl: ImageProviderImpl): ImageProvider
}
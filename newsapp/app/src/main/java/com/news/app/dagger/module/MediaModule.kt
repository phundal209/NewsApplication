package com.news.app.dagger.module

import com.news.media.api.ImageProvider
import com.news.media.data.ImageProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class MediaModule {
    @Binds
    @Singleton
    abstract fun bindsImageProvider(impl: ImageProviderImpl): ImageProvider
}
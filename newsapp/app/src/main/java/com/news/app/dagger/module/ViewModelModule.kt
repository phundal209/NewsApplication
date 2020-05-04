package com.news.app.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news.app.dagger.annotation.ViewModelKey
import com.news.app.dagger.factory.DaggerViewModelFactory
import com.news.news.presentation.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindsViewModelFactory(impl: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindsNewsViewModel(impl: NewsViewModel): ViewModel
}
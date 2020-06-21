package com.news.app.hilt.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news.app.hilt.annotation.ViewModelKey
import com.news.app.hilt.factory.DaggerInjectionViewModelFactory
import com.news.news.presentation.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindsViewModelFactory(impl: DaggerInjectionViewModelFactory):
            ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(impl: NewsViewModel): ViewModel
}
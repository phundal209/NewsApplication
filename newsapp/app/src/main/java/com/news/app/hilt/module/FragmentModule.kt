package com.news.app.hilt.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.news.app.hilt.annotation.FragmentKey
import com.news.app.hilt.factory.FragmentInjectionFactory
import com.news.news.presentation.view.NewsFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class FragmentModule {

    @Binds
    abstract fun bindsFragmentFactory(impl: FragmentInjectionFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(NewsFragment::class)
    abstract fun bindNewsFragment(impl: NewsFragment): Fragment

}
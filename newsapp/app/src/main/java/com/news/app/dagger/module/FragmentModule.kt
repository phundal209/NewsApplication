package com.news.app.dagger.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.news.app.dagger.annotation.FragmentKey
import com.news.app.dagger.factory.FragmentInjectionFactory
import com.news.news.presentation.NewsFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentModule {

    @Binds
    abstract fun bindsFragmentFactory(impl: FragmentInjectionFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(NewsFragment::class)
    abstract fun bindNesFragment(impl: NewsFragment): Fragment

}
package com.news.app.dagger.module

import com.news.app.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}
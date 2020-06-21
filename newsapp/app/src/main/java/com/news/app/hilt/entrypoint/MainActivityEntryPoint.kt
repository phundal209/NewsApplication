package com.news.app.hilt.entrypoint

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface MainActivityEntryPoint {
    fun getFragmentFactory(): FragmentFactory
}
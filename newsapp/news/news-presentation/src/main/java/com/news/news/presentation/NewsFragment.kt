package com.news.news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.news.media.api.ImageProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment @Inject constructor(
    private val imageProvider: ImageProvider
) : Fragment() {
    private val newsViewModel: NewsViewModel by viewModels()

    private val observer = Observer<NewsViewState> {
        when(it) {
            is NewsViewState.Loading -> {}
            is NewsViewState.Successs -> {}
            is NewsViewState.Error -> {}
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        newsViewModel.newsViewStateLiveData.observe(viewLifecycleOwner, observer)
    }
}
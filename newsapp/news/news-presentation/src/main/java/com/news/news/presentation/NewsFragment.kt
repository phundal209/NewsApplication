package com.news.news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.news.media.api.ImageProvider
import com.news.news.api.NewsCategories
import com.news.news.presentation.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment @Inject constructor(
    private val imageProvider: ImageProvider,
    private val newsViewModel: NewsViewModel
) : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val observer = Observer<NewsViewState> {
        when(it) {
            is NewsViewState.Loading -> {}
            is NewsViewState.Success -> {}
            is NewsViewState.Error -> {}
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        newsViewModel.newsViewStateLiveData.observe(viewLifecycleOwner, observer)

        newsViewModel.getTopHeadlines("us", NewsCategories.Sports, null,
            "nba", null, null)
    }
}
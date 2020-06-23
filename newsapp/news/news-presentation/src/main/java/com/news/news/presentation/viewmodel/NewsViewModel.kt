package com.news.news.presentation.viewmodel

import androidx.lifecycle.*
import com.news.news.api.NewsCategory
import com.news.news.presentation.NewsViewState
import com.news.news.presentation.repository.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {
    val newsResponseLiveData: LiveData<NewsViewState> = newsRepository.getHeadlinesLiveData()

    fun getHotNews(category: NewsCategory, query: String? = null) {
        viewModelScope.launch {
            newsRepository.getHotHeadlines(category, query)
        }
    }
}
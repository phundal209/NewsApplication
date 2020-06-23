package com.news.news.presentation

import androidx.lifecycle.*
import com.news.news.api.NewsCategory
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {
    val newsResponseLiveData: LiveData<NewsObservableState> = newsRepository.getHeadlinesLiveData()

    fun getHotNews(category: NewsCategory) {
        viewModelScope.launch {
            newsRepository.getHotHeadlines(category)
        }
    }
}
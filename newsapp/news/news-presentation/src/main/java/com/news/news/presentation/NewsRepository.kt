package com.news.news.presentation

import androidx.lifecycle.LiveData
import com.news.news.api.NewsCategory

interface NewsRepository {
    suspend fun getHotHeadlines(category: NewsCategory, query: String? = null)

    fun getHeadlinesLiveData(): LiveData<NewsObservableState>
}
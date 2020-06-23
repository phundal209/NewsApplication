package com.news.news.presentation

import androidx.lifecycle.LiveData
import com.news.news.api.NewsCategory

interface NewsRepository {
    suspend fun getHotHeadlines(category: NewsCategory)

    fun getHeadlinesLiveData(): LiveData<NewsObservableState>
}
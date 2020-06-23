package com.news.news.presentation.repository

import androidx.lifecycle.LiveData
import com.news.news.api.NewsCategory
import com.news.news.presentation.NewsViewState

interface NewsRepository {
    suspend fun getHotHeadlines(category: NewsCategory, query: String? = null)

    fun getHeadlinesLiveData(): LiveData<NewsViewState>
}
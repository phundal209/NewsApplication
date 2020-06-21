package com.news.news.presentation

import androidx.paging.PagedList
import com.news.news.api.NewsResponse

sealed class NewsViewState {
    object Loading: NewsViewState()
    data class Success(val response: NewsResponse): NewsViewState()
    data class Error(val message: String): NewsViewState()
}
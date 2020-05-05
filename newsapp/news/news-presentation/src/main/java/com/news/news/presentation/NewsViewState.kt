package com.news.news.presentation

import com.news.news.api.NewsResponse

sealed class NewsViewState {
    object Loading: NewsViewState()
    data class Successs(val response: NewsResponse): NewsViewState()
    data class Error(val message: String): NewsViewState()
}
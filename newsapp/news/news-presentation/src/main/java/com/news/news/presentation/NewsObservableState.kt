package com.news.news.presentation

import com.news.news.api.NewsArticle

sealed class NewsObservableState {
    object Loading: NewsObservableState()
    data class Success(val articles: List<NewsArticle>): NewsObservableState()
    data class Error(val message: String): NewsObservableState()
}
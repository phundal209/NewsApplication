package com.news.news.presentation

import com.news.news.api.NewsArticle

sealed class NewsViewState {
    object Loading: NewsViewState()
    data class Success(val articles: List<NewsArticle>): NewsViewState()
    data class Error(val message: String): NewsViewState()
}
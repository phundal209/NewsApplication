package com.news.news.presentation

interface NewsItemCallback {
    fun onNewsArticleClicked(articleUrl: String)
}
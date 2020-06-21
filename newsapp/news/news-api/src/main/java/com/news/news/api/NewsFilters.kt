package com.news.news.api

/**
 * Filters news by the sortBy filter of the option selected here
 */
enum class NewsFilters(val filter: String) {
    Relevency("relevancy"),
    Popularity("popularity"),
    PublishedAt("publishedAt")
}
package com.news.news.api

/**
 * The category you want to get the headlines for.
 * Note: Do not mix this param with the sources param.
 */
enum class NewsCategories(categorySymbol: String) {
    Business("business"),
    Entertainment("entertainment"),
    General("general"),
    Health("health"),
    Science("science"),
    Sports("sports"),
    Technology("technology");
}
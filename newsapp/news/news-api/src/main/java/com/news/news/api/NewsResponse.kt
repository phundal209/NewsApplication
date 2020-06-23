package com.news.news.api

/**
 * Data model representation of a news response.
 *
 * @param status: If the request was successful or not. Options: ok, error. In the case of error a code and message property will be populated.
 *
 * @param totalResults: The total number of results available for your request.
 *
 * @param articles: The results of the request.
 *
 * @param source: The identifier id and a display name name for the source this article came from.
 *
 * @param author: The author of the article
 *
 * @param title: The headline or title of the article.
 *
 * @param description: A description or snippet from the article.
 *
 * @param url: The direct URL to the article.
 *
 * @param urlToImage: The URL to a relevant image for the article.
 *
 * @param publishedAt: The date and time that the article was published, in UTC (+000)
 *
 * @param content: The unformatted content of the article, where available. This is truncated to 260 chars for Developer plan users.
 */
data class NewsResponse(
    val status: NewsStatus,
    val totalResults: Int,
    val articles: List<NewsArticle>
)

sealed class NewsStatus {
    object Success: NewsStatus()
    data class Error(val message: String, val code: String): NewsStatus()
}

data class NewsArticle(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class Source(
    val id: String,
    val name: String
)
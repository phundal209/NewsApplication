package com.news.news.data

import com.news.news.api.NewsArticle
import com.news.news.api.NewsResponse
import com.news.news.api.NewsStatus
import com.news.news.api.Source
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponseDto(
    @field:Json(name = "status") val status: String,
    @field:Json(name = "code") val code: String?,
    @field:Json(name = "message") val message: String?,
    @field:Json(name = "totalResults") val totalResults: Int,
    @field:Json(name = "articles") val articles : List<ArticlesDto>
)

@JsonClass(generateAdapter = true)
data class ArticlesDto(
    @field:Json(name = "source") val source: SourceDto,
    @field:Json(name = "author") val author: String?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "url") val url: String?,
    @field:Json(name = "urlToImage") val urlToImage: String?,
    @field:Json(name = "publishedAt") val publishedAt: String?,
    @field:Json(name = "content") val content: String?
)

fun ArticlesDto.toArticle(): NewsArticle {
    return NewsArticle(
        source = source.toSource(),
        author = author.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        urlToImage = urlToImage.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        content = content.orEmpty()
    )
}

@JsonClass(generateAdapter = true)
data class SourceDto(
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "name") val name: String?
)

fun SourceDto.toSource(): Source {
    return Source(
        id = id.orEmpty(),
        name = name.orEmpty()
    )
}

fun NewsResponseDto.toNewsResponse() : NewsResponse {
    var newsStatus: NewsStatus? = null
    when (status) {
        "ok" -> {
            newsStatus = NewsStatus.Success
        }
        "error" -> {
            newsStatus = NewsStatus.Error(message = message.orEmpty(), code = code.orEmpty())
        }
    }
    return NewsResponse(
        status = newsStatus!!,
        totalResults = totalResults,
        articles = articles.map { it.toArticle() }
    )
}
package com.news.news.data

import com.news.news.api.NewsApi
import com.news.news.api.NewsCategories
import com.news.news.api.NewsFilters
import com.news.news.api.NewsResponse
import retrofit2.Retrofit
import javax.inject.Inject

class NewsApiImpl @Inject constructor(
    retrofit: Retrofit
) : NewsApi {
    private val newsRestApi = retrofit.create(NewsRestApi::class.java)

    override suspend fun getTopHeadlines(
        country: String?,
        category: NewsCategories?,
        sources: String?,
        query: String?,
        pageSize: Int?,
        page: Int?
    ): Result<NewsResponse> {
        return newsRestApi.runCatching {
            getTopNews(
                country = country,
                sources = sources,
                query = query,
                pageSize = pageSize,
                page = page,
                category = category?.name).toNewsResponse()
        }
    }

    override suspend fun getAllNews(
        query: String?,
        queryInTitle: String?,
        sources: String?,
        domains: String?,
        excludeDomains: String?,
        from: String?,
        to: String?,
        language: String?,
        sortBy: NewsFilters?,
        pageSize: Int?,
        page: Int?
    ): Result<NewsResponse> {
        return newsRestApi.runCatching {
            getEverything(
                query = query,
                queryInTitle = queryInTitle,
                sources = sources,
                domains = domains,
                excludeDomains = excludeDomains,
                toTime = to,
                fromTime = from,
                sortBy = sortBy?.filter,
                page = page,
                pageSize = pageSize
            ).toNewsResponse()
        }
    }
}
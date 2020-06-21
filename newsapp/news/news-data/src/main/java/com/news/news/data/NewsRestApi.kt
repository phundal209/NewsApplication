package com.news.news.data

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRestApi {

    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String = "fe24cb8ea1314ce48fb048dcbb884d8a",
        @Query("page") page : Int?,
        @Query("pageSize") pageSize: Int?,
        @Query("q") query: String?,
        @Query("sources") sources: String?,
        @Query("category") category: String?
    ): NewsResponseDto


    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String? = "sports",
        @Query("apiKey") apiKey: String = "fe24cb8ea1314ce48fb048dcbb884d8a",
        @Query("page") page : Int?,
        @Query("pageSize") pageSize: Int?,
        @Query("sources") sources: String?,
        @Query("qInTitle") queryInTitle: String?,
        @Query("domains") domains: String?,
        @Query("excludeDomains") excludeDomains: String?,
        @Query("to") toTime: String?,
        @Query("from") fromTime: String?,
        @Query("sortBy") sortBy: String?
    ): NewsResponseDto
}
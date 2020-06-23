//package com.news.news.presentation
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.paging.DataSource
//import com.news.news.api.NewsApi
//import com.news.news.api.NewsArticle
//import com.news.news.api.NewsCategory
//
//class NewsDataFactory(private val newsApi: NewsApi, private var category: NewsCategory)
//    : DataSource.Factory<Int, NewsArticle>() {
//    private val _newsDataSourceLiveData: MutableLiveData<NewsDataSource> = MutableLiveData()
//    val newsDataSourceLiveData: LiveData<NewsDataSource>
//        get() = _newsDataSourceLiveData
//
//    override fun create(): DataSource<Int, NewsArticle> {
//        val newsDataSource = NewsDataSource(newsApi, category)
//        _newsDataSourceLiveData.postValue(newsDataSource)
//        return newsDataSource
//    }
//
//    fun updateCategory(category: NewsCategory) {
//        this.category = category
//    }
//}
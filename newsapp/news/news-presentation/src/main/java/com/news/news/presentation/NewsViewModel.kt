package com.news.news.presentation

import androidx.lifecycle.*
import com.news.news.api.NewsApi
import com.news.news.api.NewsCategory
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsApi: NewsApi
): ViewModel() {
    private val _newsResponseLiveData: MutableLiveData<NewsObservableState> = MutableLiveData()
    val newsResponseLiveData: LiveData<NewsObservableState>
        get() = _newsResponseLiveData

    fun getHotNews(category: NewsCategory) {
        _newsResponseLiveData.postValue(NewsObservableState.Loading)
        viewModelScope.launch {
            val headlinesResponse = newsApi.getTopHeadlines(category)
            headlinesResponse.onSuccess {
                _newsResponseLiveData.postValue(NewsObservableState.Success(it.articles))
            }
            headlinesResponse.onFailure {
                _newsResponseLiveData.postValue(NewsObservableState.Error(it.message ?: "Api Error"))
            }
        }
    }
//    private val newsDataFactory = NewsDataFactory(newsApi, NewsCategory.Business)
//    val observableLiveData: LiveData<NewsNetworkState> =
//        Transformations.switchMap(newsDataFactory.newsDataSourceLiveData) {
//            it.newsViewStateLiveData
//        }
//    var newsArticleLiveData: LiveData<PagedList<NewsArticle>>
//
//    init {
//        val executor = Executors.newFixedThreadPool(5)
//        val pagedListConfig = PagedList.Config.Builder()
//            .setEnablePlaceholders(false)
//            .setInitialLoadSizeHint(10)
//            .setPageSize(20)
//            .build()
//        newsArticleLiveData = LivePagedListBuilder(newsDataFactory, pagedListConfig)
//            .setFetchExecutor(executor).build()
//    }
//
//    fun getTopHeadlines(category: NewsCategory) {
//        newsDataFactory.updateCategory(category)
//        newsDataFactory.newsDataSourceLiveData.value?.invalidate()
//    }
}
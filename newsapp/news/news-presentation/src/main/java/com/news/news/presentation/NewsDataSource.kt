//package com.news.news.presentation
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.paging.PageKeyedDataSource
//import com.news.news.api.NewsApi
//import com.news.news.api.NewsArticle
//import com.news.news.api.NewsCategory
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//
//class NewsDataSource(private val newsApi: NewsApi, private val category: NewsCategory):
//    PageKeyedDataSource<Int, NewsArticle>() {
//    private val _newsViewStateLiveData: MutableLiveData<NewsObservableState> = MutableLiveData()
//    val newsViewStateLiveData: LiveData<NewsObservableState>
//        get() = _newsViewStateLiveData
//
//    override fun loadInitial(
//        params: LoadInitialParams<Int>,
//        callback: LoadInitialCallback<Int, NewsArticle>
//    ) {
//        _newsViewStateLiveData.postValue(NewsObservableState.Loading)
//        GlobalScope.launch {
//            val response = newsApi.getTopHeadlines(category)
//            response.onSuccess {
//                callback.onResult(it.articles, null, 21)
//                _newsViewStateLiveData.postValue(NewsObservableState.Loaded)
//            }
//            response.onFailure {
//                _newsViewStateLiveData.postValue(NewsObservableState.Error(it.message ?: "API Error"))
//            }
//        }
//
//    }
//
//    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsArticle>) {
//        GlobalScope.launch {
//            val response = newsApi.getTopHeadlines(category = category,
//                                                    page = params.key,
//                                                    pageSize = params.requestedLoadSize)
//
//            response.onSuccess {
//                val nextPageKey = params.key + 1
//                callback.onResult(it.articles, nextPageKey)
//                _newsViewStateLiveData.postValue(NewsObservableState.Loaded)
//            }
//            response.onFailure {
//                _newsViewStateLiveData.postValue(NewsObservableState.Error(it.message ?: "API Error"))
//            }
//        }
//
//    }
//
//    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewsArticle>) {
//        // do nothing
//    }
//}
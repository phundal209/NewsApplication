package com.news.news.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.news.news.api.NewsApi
import com.news.news.api.NewsCategory
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    private val _newsObservableStateLiveData: MutableLiveData<NewsObservableState> = MutableLiveData()
    private val newsObservableStateLiveData: LiveData<NewsObservableState>
        get() = _newsObservableStateLiveData

    override suspend fun getHotHeadlines(category: NewsCategory, query: String?){
        _newsObservableStateLiveData.value = NewsObservableState.Loading
        val response = newsApi.getTopHeadlines(category, query)
        response.onSuccess {
            _newsObservableStateLiveData.value = NewsObservableState.Success(it.articles)
        }
        response.onFailure {
            _newsObservableStateLiveData.value = NewsObservableState.Error(it.message ?: "Api error")
        }
    }

    override fun getHeadlinesLiveData(): LiveData<NewsObservableState> =
        newsObservableStateLiveData
}
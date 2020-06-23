package com.news.news.presentation.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.news.news.api.NewsApi
import com.news.news.api.NewsCategory
import com.news.news.presentation.NewsViewState
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    private val _newsObservableStateLiveData: MutableLiveData<NewsViewState> = MutableLiveData()
    private val newsObservableStateLiveData: LiveData<NewsViewState>
        get() = _newsObservableStateLiveData

    override suspend fun getHotHeadlines(category: NewsCategory, query: String?){
        _newsObservableStateLiveData.value =
            NewsViewState.Loading
        val response = newsApi.getTopHeadlines(category, query)
        response.onSuccess {
            _newsObservableStateLiveData.value =
                NewsViewState.Success(it.articles)
        }
        response.onFailure {
            _newsObservableStateLiveData.value =
                NewsViewState.Error(
                    it.message ?: "Api error"
                )
        }
    }

    override fun getHeadlinesLiveData(): LiveData<NewsViewState> =
        newsObservableStateLiveData
}
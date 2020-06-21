package com.news.news.presentation

import androidx.lifecycle.*
import com.news.news.api.NewsApi
import com.news.news.api.NewsCategories
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsApi: NewsApi
): ViewModel() {
    private val _newsViewStateLiveData: MutableLiveData<NewsViewState> = MutableLiveData()
    val newsViewStateLiveData: LiveData<NewsViewState>
        get() = _newsViewStateLiveData


    fun getTopHeadlines(
        country: String?,
        category: NewsCategories?,
        sources: String?,
        query: String?,
        pageSize: Int? ,
        page: Int?
    ) {
        _newsViewStateLiveData.postValue(NewsViewState.Loading)
        viewModelScope.launch {
            val topHeadlines = newsApi.getTopHeadlines(
                country = country,
                category = category,
                sources = sources,
                query = query,
                pageSize = pageSize,
                page = page
            )
            topHeadlines.onSuccess {
                _newsViewStateLiveData.postValue(NewsViewState.Success(it))
            }
            topHeadlines.onFailure {
                _newsViewStateLiveData.postValue(NewsViewState.Error(it.message ?: "Api Failure"))
            }
        }
    }
}
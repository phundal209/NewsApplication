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
}
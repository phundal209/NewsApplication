package com.news.news.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.news.news.api.NewsCategories
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
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
        viewModelScope.launch {

        }
    }
}
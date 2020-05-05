package com.news.news.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.news.api.NewsCategories
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
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
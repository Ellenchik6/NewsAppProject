package com.example.projectpart1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataLoaderViewModel : ViewModel() {
    private val _newsLiveData: MutableLiveData<List<ArticleResponse>> = MutableLiveData<List<ArticleResponse>>()
    val newsLiveData: LiveData<List<ArticleResponse>> = _newsLiveData
    private val newsApiService = RetrofitHelper.getInstance().create(NewsApiService::class.java)
    private val newsRepo by lazy { NewsRepo(newsApiService) }
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
    fun loadNews() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _newsLiveData.postValue(listOf(newsRepo.fetchNews("us")))
        }
    }
}
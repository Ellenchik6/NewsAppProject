package com.example.newsapp.newsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DataLoaderViewModel : ViewModel() {
    private val _newsLiveData: MutableLiveData<NewsResponse> = MutableLiveData<NewsResponse>()
    val newsLiveData: LiveData<NewsResponse> = _newsLiveData
    private val newsApiService = RetrofitHelper.getInstance().create(NewsApiService::class.java)


    fun loadNews() {
        viewModelScope.launch {
            try {
                val response = NewsRepo(newsApiService).fetchNews("us")
                _newsLiveData.postValue(response)
            } catch (e: Exception) {
                Log.d(">>>", e.message.toString())
            }
        }
    }

    fun loadNewsWithFilter(category: String) {
        viewModelScope.launch {
            try {
                val response = NewsRepo(newsApiService).fetchNewsWithCategory(category = category.lowercase())
                _newsLiveData.postValue(response)
            } catch (e: Exception) {
                Log.d(">>>", e.message.toString())
            }
        }
    }

    fun loadNewsWithSearch(q: String) {
        viewModelScope.launch {
            try {
                val response = NewsRepo(newsApiService).fetchNewsWithQ(q = q.lowercase())
                _newsLiveData.postValue(response)
            } catch (e: Exception) {
                Log.d(">>>", e.message.toString())
            }
        }
    }
}
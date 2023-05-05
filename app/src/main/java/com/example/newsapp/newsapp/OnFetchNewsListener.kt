package com.example.newsapp.newsapp

interface OnFetchNewsListener<NewsResponse> {
    fun onFetchNews(newsList : List<NewsResponse>)
    fun onError(message : String)
}
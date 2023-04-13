package com.example.projectpart1

interface OnFetchNewsListener<ArticleResponse> {
    fun onFetchNews(newsList : List<ArticleResponse>)
    fun onError(message : String)
}
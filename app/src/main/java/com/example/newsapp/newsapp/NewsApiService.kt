package com.example.newsapp.newsapp

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines?country=us&apiKey=c981e92db48a4889bc46d4505b8098d4")
    suspend fun fetchNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("q") q: String,
        @Query("results") results: Int = 10
    ): NewsResponse

}
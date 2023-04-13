package com.example.projectpart1

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines?country=us&apiKey=8a463801ffd74330a9e6b74316c7ab1e")
    suspend fun fetchNews(@Query("country") country: String): Response<ArticleResponse>

}
package com.example.projectpart1



class NewsRepo(private val newsApiService: NewsApiService) {

    private val retrofit by lazy { RetrofitHelper.getInstance() }
    suspend fun fetchNews(country: String): ArticleResponse {
        val apiService = retrofit.create(newsApiService::class.java)
        return apiService.fetchNews(country).run {
            this.body() ?: throw Exception("news not found")
        }
    }

}
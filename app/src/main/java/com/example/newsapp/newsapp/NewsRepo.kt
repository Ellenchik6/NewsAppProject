package com.example.newsapp.newsapp


class NewsRepo(private val newsApiService: NewsApiService) {
    suspend fun fetchNews(country: String): NewsResponse {
        return RetrofitHelper.getInstance()
            .create(NewsApiService::class.java).fetchNews(country = country, "", "")
    }

    suspend fun fetchNewsWithCategory(category: String): NewsResponse {
        return RetrofitHelper.getInstance()
            .create(NewsApiService::class.java).fetchNews("", category = category, "")
    }

    suspend fun fetchNewsWithQ(q: String): NewsResponse {
        return RetrofitHelper.getInstance().create(NewsApiService::class.java).fetchNews("","", q = q)
    }

}
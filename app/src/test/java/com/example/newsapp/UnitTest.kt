package com.example.newsapp

import com.example.newsapp.newsapp.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UnitTest {
    private lateinit var newsApiService:NewsApiService
    private lateinit var newsRepository: NewsRepo
    private lateinit var sourceResponse: SourceResponse

    @Before
    fun setup() {
        newsApiService = mockk()

        newsRepository = NewsRepo(newsApiService)
    }

    @Test
    fun `fetchNews returns list of news articles`() = runBlocking {
        val mockNewsResponse = NewsResponse(
            articles = listOf(
                ArticleResponse(
                    title = "News 1",
                    description = "Description 1",
                    author = "Author 1",
                    publishedAt = "PublishedAt 1",
                    urlToImage = "UrlToImage 1",
                    url = "Url 1",
                    content = "Content 1",
                    source = sourceResponse
                ),
                ArticleResponse(
                    title = "News 2",
                    description = "Description 2",
                    author = "Author 2",
                    publishedAt = "PublishedAt 2",
                    urlToImage = "UrlToImage 2",
                    url = "Url 2",
                    content = "Content 2",
                    source = sourceResponse
                )
            ),
            status = "",
            totalResults = 10
        )
        coEvery { newsApiService.fetchNews("","","") } returns mockNewsResponse

        val result = newsRepository.fetchNews("")

        assertEquals(mockNewsResponse.articles.size, result.totalResults)
        assertEquals(mockNewsResponse.articles[0].title, result.articles[0].title)
        assertEquals(mockNewsResponse.articles[1].description, result.articles[1].description)
    }
}

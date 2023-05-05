package com.example.newsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newsapp.Screens
import com.example.newsapp.newsapp.NewsResponse


@Composable
fun NewsList(navController: NavController, allNews: List<NewsResponse>) {

    LazyColumn {
        itemsIndexed(allNews) { index, news ->
            NewsCard(newsResponse = news) {
                navController.navigate(Screens.DetailScreen.route + "/$index")
            }
        }
    }
}

@Composable
private fun NewsCard(newsResponse: NewsResponse, onClick: () -> Unit) {
    for (article in newsResponse.articles) {
        Card(
            backgroundColor = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(16.dp)
                .clickable(onClick = onClick)
                .clip(shape = RoundedCornerShape(5.dp)),
            elevation = 4.dp
        ) {
            Box(Modifier.fillMaxSize()) {
                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = "avatar",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = article.title,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.subtitle1
        )
    }
}
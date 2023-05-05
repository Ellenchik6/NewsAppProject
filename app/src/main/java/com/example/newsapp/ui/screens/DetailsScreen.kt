package com.example.newsapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newsapp.newsapp.ArticleResponse

@Composable
fun DetailsScreen(navController: NavController, articleResponse: ArticleResponse) {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Details", fontWeight = FontWeight.Bold)
            }
        }
    }) {it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Source: : " + articleResponse.source.name, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
            articleResponse.author?.let { it1 -> Text("Author: $it1", fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic) }
            AsyncImage(
                model = articleResponse.urlToImage,
                contentDescription = "avatar",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(30.dp))
                    .height(250.dp)
                    .width(250.dp)
            )

            Text("Title: \n" + articleResponse.title, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Text("\n Published at: " + articleResponse.publishedAt)
            articleResponse.description.toString().let { it1 -> Text("\n Description: $it1") }
            articleResponse.content.toString().let { it1 -> Text("\n Content: $it1") }
        }
    }
}
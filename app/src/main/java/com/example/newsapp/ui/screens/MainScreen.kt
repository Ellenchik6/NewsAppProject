package com.example.newsapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newsapp.newsapp.Filter
import com.example.newsapp.newsapp.NewsResponse
import com.example.newsapp.ui.components.FilterButton
import com.example.newsapp.ui.components.SearchBar
import com.example.newsapp.ui.components.UsersRefreshableLazyColumn

@Composable
fun MainScreen(
    navController:  NavHostController,
    news: List<NewsResponse>,
    onRefresh: () -> Unit,
    onSearch: (String) -> Unit,
    onSelectFilter: (String) -> Unit) {
    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            FilterButton(
                filters = listOf(
                    Filter(1, "Business"),
                    Filter(2, "Entertainment"),
                    Filter(3, "General"),
                    Filter(4, "Health"),
                ),
                onFilterSelected = { filter ->
                    onSelectFilter(filter.name)
                }
            )

            SearchBar(onSearch = { query ->
                onSearch(query)
                Log.d(">>>", query)
            }, news, onSelectFilter)
        }

        UsersRefreshableLazyColumn(
            data = news,
            onRefresh = onRefresh,
            navController = navController
        )
    }
}
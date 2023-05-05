package com.example.newsapp.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.newsapp.newsapp.NewsResponse
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun UsersRefreshableLazyColumn(
    data: List<NewsResponse>,
    onRefresh: () -> Unit,
    navController: NavController,
) {
    val state = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = state,
        onRefresh = {
            onRefresh()
            state.isRefreshing = false
        }
    ) {
        NewsList(navController, data)
    }
}
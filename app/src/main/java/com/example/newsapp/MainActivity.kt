package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.newsapp.DataLoaderViewModel
import com.example.newsapp.ui.screens.DetailsScreen
import com.example.newsapp.ui.screens.MainScreen

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<DataLoaderViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadNews()
        viewModel.newsLiveData.observe(this) { news ->
            setContent {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screens.HomeScreen.route
                ) {

                    // Home Screen
                    composable(route = Screens.HomeScreen.route) {
                        MainScreen(
                            navController = navController,
                            news = listOf(news),
                            onRefresh = viewModel::loadNews,
                            onSearch = viewModel::loadNewsWithSearch,
                            onSelectFilter = {
                                viewModel.loadNewsWithFilter(it)
                            }
                        )
                    }

                    // Details Screen
                    composable(
                        route = Screens.DetailScreen.route + "/{index}",
                        arguments = listOf(navArgument(name = "index") {
                            type = NavType.IntType
                        })
                    ) { entry ->
                        val index = entry.arguments?.getInt("index")

                        index?.let {
                            news.articles[it]
                        }?.let {
                            DetailsScreen(navController, it)
                        }
                    }
                }
            }
        }
    }
}
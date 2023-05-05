package com.example.newsapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsapp.newsapp.Filter
import com.example.newsapp.newsapp.NewsResponse

@Composable
fun SearchBar(
    onSearch: (String) -> Unit,
    news: List<NewsResponse>,
    onSelectFilter: (String) -> Unit
) {
    var searchValue by remember { mutableStateOf(TextFieldValue()) }
    var buttonClicked by remember { mutableStateOf(false) }

    if (searchValue.toString().isNotEmpty() && !searchValue.equals(news) && buttonClicked) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilterButton(
                    filters = listOf(
                        Filter(1, "Business"),
                        Filter(2, "Entertainment"),
                        Filter(3, "General"),
                        Filter(4, "Health")
                    ),
                    onFilterSelected = { filter ->
                        onSelectFilter(filter.name)
                    }
                )

                Card(
                    backgroundColor = Color.White,
                    elevation = 4.dp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextField(
                            value = searchValue,
                            onValueChange = {
                                searchValue = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Search
                            ),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    buttonClicked = true
                                    onSearch(searchValue.text)
                                }
                            ),
                            placeholder = {
                                Text("Search...")
                            },
                            singleLine = true,
                            maxLines = 1
                        )

                        if (searchValue.text.isNotEmpty()) {
                            IconButton(onClick = {
                                searchValue = TextFieldValue()
                            }) {
                                Icon(
                                    Icons.Filled.Clear,
                                    contentDescription = "Clear icon"
                                )
                            }
                        }
                    }
                }

                IconButton(onClick = {
                    buttonClicked = true
                    onSearch(searchValue.text)
                }) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Search icon"
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Results Found",
                    modifier = Modifier.wrapContentSize(),
                    textAlign = TextAlign.Center
                )
            }
        }

        buttonClicked = false
    } else {
        Card(
            backgroundColor = Color.White,
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row {
                TextField(
                    value = searchValue,
                    onValueChange = {
                        searchValue = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            buttonClicked = true
                            onSearch(searchValue.text)
                        }
                    ),
                    placeholder = {
                        Text("Search...")
                    },
                    singleLine = true,
                    maxLines = 1,
                    leadingIcon = {
                        Button(onClick = {
                            buttonClicked = true
                            onSearch(searchValue.text)
                        }) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "Search icon"
                            )
                        }
                    },
                    trailingIcon = {
                        if (searchValue.text.isNotEmpty()) {
                            IconButton(onClick = {
                                searchValue = TextFieldValue()
                            }) {
                                Icon(
                                    Icons.Filled.Clear,
                                    contentDescription = "Clear icon"
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

package com.example.flickr.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.flickr.domain.model.Size

@Composable
fun PhotosScreen(sizes: LazyPagingItems<Size>) {

    when(sizes.loadState.refresh) {
        is LoadState.Loading -> {
            FullPageLoading()
        }
        is LoadState.Error -> {
            val error = sizes.loadState.refresh as LoadState.Error
            error.error.localizedMessage?.let { ErrorItem(message = it) }
        }

        else -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(sizes.itemCount) {i ->
                    AsyncImage(
                        model = sizes[i]!!.source,
                        contentDescription = "kitten photo",
                        modifier = Modifier
                            .padding(10.dp)
                            .width(sizes[i]!!.width.dp)
                            .height(sizes[i]!!.height.dp)
                    )
                }
                sizes.apply {
                    when(sizes.loadState.append) {
                        is LoadState.Loading -> {
                            item { ListItemsLoading() }
                        }
                        is LoadState.Error -> {
                            val error = sizes.loadState.refresh as LoadState.Error
                            item { error.error.localizedMessage?.let { ErrorItem(message = it) } }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun FullPageLoading() {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .padding(8.dp),
            strokeWidth = 5.dp
        )
        Text(
            text = "Fetching data from server...",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun ListItemsLoading() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .padding(8.dp),
            strokeWidth = 5.dp
        )
    }
}

@Composable
fun ErrorItem(message: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            fontSize = 18.sp
        )
    }
}
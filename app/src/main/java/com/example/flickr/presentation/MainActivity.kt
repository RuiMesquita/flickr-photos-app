package com.example.flickr.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.flickr.presentation.screens.PhotosScreen
import com.example.flickr.presentation.ui.theme.FlickrTheme
import com.example.flickr.presentation.viewModels.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrTheme {
                val viewModel: PhotosViewModel = hiltViewModel<PhotosViewModel>()
                val sizesList = viewModel.sizesPager.collectAsLazyPagingItems()

                PhotosScreen(sizes = sizesList)
            }
        }
    }
}



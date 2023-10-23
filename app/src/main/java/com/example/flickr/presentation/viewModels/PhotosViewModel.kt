package com.example.flickr.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.flickr.common.Constants.DEFAULT_INIT_KEY
import com.example.flickr.common.Constants.PAGE_SIZE
import com.example.flickr.data.remote.PhotosPagingSource
import com.example.flickr.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
): ViewModel() {
    val sizesPager = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = 100),
        initialKey = DEFAULT_INIT_KEY,
        pagingSourceFactory = {
            PhotosPagingSource(photoRepository)
        }
    ).flow
}
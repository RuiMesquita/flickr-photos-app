package com.example.flickr.domain.repository

import com.example.flickr.domain.model.Photo
import com.example.flickr.domain.model.PhotoResponse
import com.example.flickr.domain.model.SizesResponse

interface PhotoRepository {

    suspend fun getPhotos(apiKey: String, page: Int): PhotoResponse

    suspend fun getSizes(apiKey: String, photoId: String): SizesResponse
}
package com.example.flickr.data.repository

import com.example.flickr.data.remote.PhotoApi
import com.example.flickr.domain.model.PhotoResponse
import com.example.flickr.domain.model.SizesResponse
import com.example.flickr.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImp @Inject constructor(
    private val api: PhotoApi
): PhotoRepository {
    override suspend fun getPhotos(apiKey: String, page: Int): PhotoResponse {
        return api.getPhotos(apiKey, page)
    }

    override suspend fun getSizes(apiKey: String, photoId: String): SizesResponse {
        return api.getSizes(apiKey, photoId)
    }
}
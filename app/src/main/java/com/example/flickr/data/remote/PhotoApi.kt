package com.example.flickr.data.remote

import com.example.flickr.domain.model.PhotoResponse
import com.example.flickr.domain.model.SizesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET("services/rest/?method=flickr.photos.search&tags=kitten&format=json&nojsoncallback=1")
    suspend fun getPhotos(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): PhotoResponse


    @GET("services/rest/?method=flickr.photos.getSizes&format=json&nojsoncallback=1")
    suspend fun getSizes(
        @Query("api_key") apiKey: String,
        @Query("photo_id") photoId: String,
    ): SizesResponse
}
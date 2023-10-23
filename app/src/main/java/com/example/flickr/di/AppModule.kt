package com.example.flickr.di

import com.example.flickr.common.Constants
import com.example.flickr.data.remote.PhotoApi
import com.example.flickr.data.repository.PhotoRepositoryImp
import com.example.flickr.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesPhotoApi(): PhotoApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotoApi::class.java)
    }

    @Provides
    @Singleton
    fun providePhotoRepository(api: PhotoApi): PhotoRepository {
        return PhotoRepositoryImp(api)
    }
}
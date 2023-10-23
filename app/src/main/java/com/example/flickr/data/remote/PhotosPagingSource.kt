package com.example.flickr.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.flickr.common.Constants.API_KEY
import com.example.flickr.common.Constants.DEFAULT_INIT_KEY
import com.example.flickr.domain.model.Photo
import com.example.flickr.domain.model.Photos
import com.example.flickr.domain.model.Size
import com.example.flickr.domain.repository.PhotoRepository
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PhotosPagingSource @Inject constructor(
    private val photoRepository: PhotoRepository
): PagingSource<Int, Size>() {
    private val sizesList = mutableListOf<Size>()

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Size> {
        return try {
            val currentPage = params.key ?: DEFAULT_INIT_KEY
            val photos = getPhotos(currentPage)

            sizesList.clear()
            for (photo: Photo in photos.photo) {
                val size = getPhotoSizeById(photo.id)
                size?.let { sizesList.add(size) }
            }
            LoadResult.Page(
                data = sizesList,
                prevKey = null,
                nextKey = photos.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Size>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private suspend fun getPhotos(page: Int): Photos {
        val result = photoRepository.getPhotos(API_KEY, page)
        return result.photos
    }

    private suspend fun getPhotoSizeById(photoId: String): Size? {
        val sizes = photoRepository.getSizes(API_KEY, photoId).sizes.size
        sizes.onEach { size ->
            run {
                if (size.label.contentEquals("Large Square"))
                    return size
            }
        }
        return null
    }
}
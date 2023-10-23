package com.example.flickr.domain.model

import com.google.gson.annotations.SerializedName

data class Photo(
    val farm: Int,
    val id: String,
    @SerializedName("isfamily")
    val isFamily: Int,
    @SerializedName("isfriend")
    val isFriend: Int,
    @SerializedName("ispublic")
    val isPublic: Int,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String
)
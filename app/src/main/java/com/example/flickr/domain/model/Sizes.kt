package com.example.flickr.domain.model

data class Sizes(
    val canblog: Int,
    val candownload: Int,
    val canprint: Int,
    val size: List<Size>
)
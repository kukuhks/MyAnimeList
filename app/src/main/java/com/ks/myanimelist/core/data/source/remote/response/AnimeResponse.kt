package com.ks.myanimelist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @field:SerializedName("mal_id")
    val malId: Int,

    @field:SerializedName("images")
    val images: ImagesResponse,

    @field:SerializedName("title_english")
    val title: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("synopsis")
    val synopsis: String?,

    @field:SerializedName("episodes")
    val episodes: Int,

    @field:SerializedName("score")
    val score: Double,

    @field:SerializedName("rating")
    val rating: String,

    @field:SerializedName("year")
    val year: Int
)

data class ImagesResponse(
    @field:SerializedName("jpg")
    val jpg: JpgResponse
)

data class JpgResponse(
    @field:SerializedName("image_url")
    val imageUrl: String
)
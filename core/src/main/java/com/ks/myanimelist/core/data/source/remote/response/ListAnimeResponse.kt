package com.ks.myanimelist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListAnimeResponse(
//    @field:SerializedName("error")
//    val error: Boolean,
//
//    @field:SerializedName("message")
//    val message: String,

    @field:SerializedName("data")
    val listAnime: List<com.ks.myanimelist.core.data.source.remote.response.AnimeResponse>
)

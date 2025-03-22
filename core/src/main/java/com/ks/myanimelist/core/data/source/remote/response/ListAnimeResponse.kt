package com.ks.myanimelist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListAnimeResponse(
    @field:SerializedName("data")
    val listAnime: List<AnimeResponse>
)

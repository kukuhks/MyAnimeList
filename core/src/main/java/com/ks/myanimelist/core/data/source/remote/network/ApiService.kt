package com.ks.myanimelist.core.data.source.remote.network


import com.ks.myanimelist.core.data.source.remote.response.ListAnimeResponse
import retrofit2.http.GET


interface ApiService {
    @GET("seasons/now")
    suspend fun getList(): ListAnimeResponse
}
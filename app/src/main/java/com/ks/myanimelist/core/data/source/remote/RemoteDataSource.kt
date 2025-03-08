package com.ks.myanimelist.core.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ks.myanimelist.core.data.source.remote.network.ApiResponse
import com.ks.myanimelist.core.data.source.remote.network.ApiService
import com.ks.myanimelist.core.data.source.remote.response.AnimeResponse

class RemoteDataSource private constructor(private val apiService: ApiService){
    companion object{
        @Volatile
        private var instance : RemoteDataSource? = null
        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllAnime(): LiveData<ApiResponse<List<AnimeResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<AnimeResponse>>>()

//        get data from remote API
        val client = apiService.getList()
        client
            .
    }
}
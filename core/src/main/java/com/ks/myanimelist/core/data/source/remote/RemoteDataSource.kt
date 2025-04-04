package com.ks.myanimelist.core.data.source.remote

import android.util.Log
import com.ks.myanimelist.core.data.source.remote.network.ApiResponse
import com.ks.myanimelist.core.data.source.remote.network.ApiService
import com.ks.myanimelist.core.data.source.remote.response.AnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService){

    fun getAllAnime(): Flow<ApiResponse<List<AnimeResponse>>> {
//        MutableLiveData<ApiResponse<List<AnimeResponse>>>()

//        get data from remote API
        return flow{
            try {
                val response = apiService.getList()
                val dataArray = response.listAnime
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
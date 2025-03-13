package com.ks.myanimelist.core.data.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ks.myanimelist.core.data.source.remote.network.ApiResponse
import com.ks.myanimelist.core.data.source.remote.network.ApiService
import com.ks.myanimelist.core.data.source.remote.response.AnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService){

    suspend fun getAllAnime(): Flow<com.ks.myanimelist.core.data.source.remote.network.ApiResponse<List<com.ks.myanimelist.core.data.source.remote.response.AnimeResponse>>> {
//        MutableLiveData<ApiResponse<List<AnimeResponse>>>()

//        get data from remote API
        return flow{
            try {
                val response = apiService.getList()
                val dataArray = response.listAnime
                if (dataArray.isNotEmpty()) {
                    emit(com.ks.myanimelist.core.data.source.remote.network.ApiResponse.Success(dataArray))
                } else {
                    emit(com.ks.myanimelist.core.data.source.remote.network.ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(com.ks.myanimelist.core.data.source.remote.network.ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
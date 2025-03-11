package com.ks.myanimelist.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ks.myanimelist.core.data.source.remote.network.ApiResponse
import com.ks.myanimelist.core.data.source.remote.network.ApiService
import com.ks.myanimelist.core.data.source.remote.response.AnimeResponse
import com.ks.myanimelist.core.data.source.remote.response.ListAnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService){
    companion object{
        @Volatile
        private var instance : RemoteDataSource? = null
        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    suspend fun getAllAnime(): Flow<ApiResponse<List<AnimeResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<AnimeResponse>>>()

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
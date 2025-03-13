package com.ks.myanimelist.core.data.source

import com.ks.myanimelist.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {
    private val result : Flow<com.ks.myanimelist.core.data.source.Resource<ResultType>> = flow {
        emit(com.ks.myanimelist.core.data.source.Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(com.ks.myanimelist.core.data.source.Resource.Loading())
            when(val apiResponse = createCall().first()) {
                is com.ks.myanimelist.core.data.source.remote.network.ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        com.ks.myanimelist.core.data.source.Resource.Success(
                            it
                        )
                    })
                }
                is com.ks.myanimelist.core.data.source.remote.network.ApiResponse.Empty -> {
                    emitAll(loadFromDB().map {
                        com.ks.myanimelist.core.data.source.Resource.Success(
                            it
                        )
                    })
                }
                is com.ks.myanimelist.core.data.source.remote.network.ApiResponse.Error -> {
                    onFetchFailed()
                    emit(com.ks.myanimelist.core.data.source.Resource.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { com.ks.myanimelist.core.data.source.Resource.Success(it) })
        }
    }
    protected open fun onFetchFailed() {}
    protected abstract fun loadFromDB(): Flow<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract suspend fun createCall(): Flow<com.ks.myanimelist.core.data.source.remote.network.ApiResponse<RequestType>>
    protected abstract suspend fun saveCallResult(data: RequestType)
    fun asFlow(): Flow<com.ks.myanimelist.core.data.source.Resource<ResultType>> = result
}
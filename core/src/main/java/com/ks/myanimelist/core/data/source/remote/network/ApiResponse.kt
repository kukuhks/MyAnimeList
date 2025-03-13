package com.ks.myanimelist.core.data.source.remote.network

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T): com.ks.myanimelist.core.data.source.remote.network.ApiResponse<T>()
    data class Error(val errorMessage: String): com.ks.myanimelist.core.data.source.remote.network.ApiResponse<Nothing>()
    data object Empty: com.ks.myanimelist.core.data.source.remote.network.ApiResponse<Nothing>()
}
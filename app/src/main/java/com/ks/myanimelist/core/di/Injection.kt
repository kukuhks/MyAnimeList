package com.ks.myanimelist.core.di

import android.content.Context
import com.ks.myanimelist.core.data.source.AnimeRepository
import com.ks.myanimelist.core.data.source.local.LocalDataSource
import com.ks.myanimelist.core.data.source.local.room.AnimeDatabase
import com.ks.myanimelist.core.data.source.remote.RemoteDataSource
import com.ks.myanimelist.core.data.source.remote.network.ApiConfig
import com.ks.myanimelist.core.domain.repository.IAnimeRepository
import com.ks.myanimelist.core.domain.usecase.AnimeInteractor
import com.ks.myanimelist.core.domain.usecase.AnimeUseCase
import com.ks.myanimelist.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): IAnimeRepository {
        val database = AnimeDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())
        val localDataSource = LocalDataSource.getInstance(database.animeDao())
        val appExecutors = AppExecutors()
        return AnimeRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideAnimeUseCase(context: Context): AnimeUseCase {
        val repository = provideRepository(context)
        return AnimeInteractor(repository)
    }
}
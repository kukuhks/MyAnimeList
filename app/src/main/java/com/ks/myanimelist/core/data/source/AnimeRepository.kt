package com.ks.myanimelist.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.ks.myanimelist.core.data.source.local.LocalDataSource
import com.ks.myanimelist.core.data.source.remote.RemoteDataSource
import com.ks.myanimelist.core.data.source.remote.network.ApiResponse
import com.ks.myanimelist.core.data.source.remote.response.AnimeResponse
import com.ks.myanimelist.core.domain.model.Anime
import com.ks.myanimelist.core.domain.repository.IAnimeRepository
import com.ks.myanimelist.core.utils.AppExecutors
import com.ks.myanimelist.core.utils.DataMapper

class AnimeRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IAnimeRepository {
    companion object {
        @Volatile
        private var instance: AnimeRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): AnimeRepository =
            instance ?: synchronized(this) {
                instance ?: AnimeRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllAnime(): LiveData<Resource<List<Anime>>> =
        object : NetworkBoundResource<List<Anime>, List<AnimeResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Anime>> {
                return localDataSource.getAllAnime().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Anime>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<AnimeResponse>>> =
                remoteDataSource.getAllAnime()

            override fun saveCallResult(data: List<AnimeResponse>) {
                val animeList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertAnime(animeList)
            }
        }.asLiveData()

    override fun getFavoriteAnime(): LiveData<List<Anime>> {
        return localDataSource.getFavoriteAnime().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteAnime(anime: Anime, state: Boolean) {
        val animeEntity = DataMapper.mapDomainTpEntities(anime)
        appExecutors.diskIO().execute { localDataSource.setFavoriteAnime(animeEntity, state)}
    }
}
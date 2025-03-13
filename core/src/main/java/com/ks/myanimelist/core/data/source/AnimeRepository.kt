package com.ks.myanimelist.core.data.source

import com.ks.myanimelist.core.data.source.local.LocalDataSource
import com.ks.myanimelist.core.data.source.remote.RemoteDataSource
import com.ks.myanimelist.core.data.source.remote.network.ApiResponse
import com.ks.myanimelist.core.data.source.remote.response.AnimeResponse
import com.ks.myanimelist.core.domain.model.Anime
import com.ks.myanimelist.core.domain.repository.IAnimeRepository
import com.ks.myanimelist.core.utils.AppExecutors
import com.ks.myanimelist.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnimeRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IAnimeRepository {

    override fun getAllAnime(): Flow<Resource<List<Anime>>> =
        object : com.ks.myanimelist.core.data.source.NetworkBoundResource<List<Anime>, List<com.ks.myanimelist.core.data.source.remote.response.AnimeResponse>>() {
            override fun loadFromDB(): Flow<List<Anime>> {
                return localDataSource.getAllAnime().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Anime>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<com.ks.myanimelist.core.data.source.remote.network.ApiResponse<List<com.ks.myanimelist.core.data.source.remote.response.AnimeResponse>>> =
                remoteDataSource.getAllAnime()

            override suspend fun saveCallResult(data: List<com.ks.myanimelist.core.data.source.remote.response.AnimeResponse>) {
                val animeList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertAnime(animeList)
            }
        }.asFlow()

    override fun getFavoriteAnime(): Flow<List<Anime>> {
        return localDataSource.getFavoriteAnime().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteAnime(anime: Anime, state: Boolean) {
        val animeEntity = DataMapper.mapDomainTpEntities(anime)
        appExecutors.diskIO().execute { localDataSource.setFavoriteAnime(animeEntity, state)}
    }
}
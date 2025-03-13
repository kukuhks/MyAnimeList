package com.ks.myanimelist.core.domain.repository

import com.ks.myanimelist.core.data.source.Resource
import com.ks.myanimelist.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {
    fun getAllAnime(): Flow<Resource<List<Anime>>>
    fun getFavoriteAnime(): Flow<List<Anime>>
    fun setFavoriteAnime(anime: Anime, state: Boolean)
}
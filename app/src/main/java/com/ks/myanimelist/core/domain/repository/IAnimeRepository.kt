package com.ks.myanimelist.core.domain.repository

import androidx.lifecycle.LiveData
import com.ks.myanimelist.core.data.source.Resource
import com.ks.myanimelist.core.domain.model.Anime

interface IAnimeRepository {
    fun getAllAnime(): LiveData<Resource<List<Anime>>>
    fun getFavoriteAnime(): LiveData<List<Anime>>
    fun setFavoriteAnime(anime: Anime, state: Boolean)
}
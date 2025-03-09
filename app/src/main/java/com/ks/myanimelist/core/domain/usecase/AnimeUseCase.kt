package com.ks.myanimelist.core.domain.usecase

import androidx.lifecycle.LiveData
import com.ks.myanimelist.core.data.source.Resource
import com.ks.myanimelist.core.domain.model.Anime

interface AnimeUseCase {
    fun getAllAnime(): LiveData<Resource<List<Anime>>>
    fun getFavoriteAnime(): LiveData<List<Anime>>
    fun setFavoriteAnime(anime: Anime, state: Boolean)
}
package com.ks.myanimelist.detail

import androidx.lifecycle.ViewModel
import com.ks.myanimelist.core.domain.model.Anime
import com.ks.myanimelist.core.domain.usecase.AnimeUseCase

class DetailAnimeViewModel(private val animeUseCase: AnimeUseCase): ViewModel() {
    fun setFavoriteAnime(anime: Anime, newStatus: Boolean) = animeUseCase.setFavoriteAnime(anime, newStatus)
}
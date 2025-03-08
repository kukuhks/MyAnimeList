package com.ks.myanimelist.detail

import androidx.lifecycle.ViewModel
import com.ks.myanimelist.core.data.source.AnimeRepository
import com.ks.myanimelist.core.domain.model.Anime

class DetailAnimeViewModel(private val animeRepository: AnimeRepository): ViewModel() {
    fun setFavoriteAnime(anime: Anime, newStatus: Boolean) = animeRepository.setFavoriteAnime(anime, newStatus)
}
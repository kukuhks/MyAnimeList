package com.ks.myanimelist.core.domain.usecase

import com.ks.myanimelist.core.domain.model.Anime
import com.ks.myanimelist.core.domain.repository.IAnimeRepository

class AnimeInteractor(private val animeRepository: IAnimeRepository): AnimeUseCase {
    override fun getAllAnime() = animeRepository.getAllAnime()

    override fun getFavoriteAnime() = animeRepository.getFavoriteAnime()

    override fun setFavoriteAnime(anime: Anime, state: Boolean) = animeRepository.setFavoriteAnime(anime, state)
}
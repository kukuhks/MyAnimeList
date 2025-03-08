package com.ks.myanimelist.home

import androidx.lifecycle.ViewModel
import com.ks.myanimelist.core.data.source.AnimeRepository

class HomeViewModel(animeRepository: AnimeRepository) : ViewModel() {
    val anime = animeRepository.getAllAnime()
}
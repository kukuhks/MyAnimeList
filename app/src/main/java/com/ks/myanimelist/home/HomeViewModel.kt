package com.ks.myanimelist.home

import androidx.lifecycle.ViewModel
import com.ks.myanimelist.core.domain.usecase.AnimeUseCase

class HomeViewModel(animeUseCase: AnimeUseCase) : ViewModel() {
    val anime = animeUseCase.getAllAnime()
}
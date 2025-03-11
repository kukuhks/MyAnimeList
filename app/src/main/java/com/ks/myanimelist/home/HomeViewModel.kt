package com.ks.myanimelist.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ks.myanimelist.core.domain.usecase.AnimeUseCase

class HomeViewModel(animeUseCase: AnimeUseCase) : ViewModel() {
    val anime = animeUseCase.getAllAnime().asLiveData()
}
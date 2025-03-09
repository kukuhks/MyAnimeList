package com.ks.myanimelist.favorite

import androidx.lifecycle.ViewModel
import com.ks.myanimelist.core.domain.usecase.AnimeUseCase

class FavoriteViewModel(animeUseCase: AnimeUseCase) : ViewModel(){
    val anime = animeUseCase.getFavoriteAnime()
}
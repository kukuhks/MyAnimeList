package com.ks.myanimelist.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ks.myanimelist.core.domain.usecase.AnimeUseCase
import com.ks.myanimelist.core.domain.usecase.SettingUseCase

class HomeViewModel(
    animeUseCase: AnimeUseCase,
    settingUseCase: SettingUseCase
) : ViewModel() {
    val anime = animeUseCase.getAllAnime().asLiveData()

    val getThemeSetting = settingUseCase.getThemeSetting().asLiveData()
}
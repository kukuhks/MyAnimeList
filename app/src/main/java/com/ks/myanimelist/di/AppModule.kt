package com.ks.myanimelist.di

import com.ks.myanimelist.core.domain.usecase.AnimeInteractor
import com.ks.myanimelist.core.domain.usecase.AnimeUseCase
import com.ks.myanimelist.detail.DetailAnimeViewModel
import com.ks.myanimelist.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AnimeUseCase> { AnimeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailAnimeViewModel(get()) }
}
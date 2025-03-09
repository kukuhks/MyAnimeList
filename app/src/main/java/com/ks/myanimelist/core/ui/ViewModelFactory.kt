package com.ks.myanimelist.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ks.myanimelist.core.data.source.AnimeRepository
import com.ks.myanimelist.core.di.Injection
import com.ks.myanimelist.core.domain.usecase.AnimeUseCase
import com.ks.myanimelist.detail.DetailAnimeViewModel
import com.ks.myanimelist.favorite.FavoriteViewModel
import com.ks.myanimelist.home.HomeViewModel

class ViewModelFactory private constructor(private val animeUseCase: AnimeUseCase) :
    ViewModelProvider.NewInstanceFactory() {

        companion object{
            @Volatile
            private var instance: ViewModelFactory? = null
            fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideAnimeUseCase(context))
                }
        }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(animeUseCase) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(animeUseCase) as T
            modelClass.isAssignableFrom(DetailAnimeViewModel::class.java) -> DetailAnimeViewModel(animeUseCase) as T
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

}
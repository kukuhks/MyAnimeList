package com.ks.myanimelist.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ks.myanimelist.core.data.source.local.entity.AnimeEntity

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime")
    fun getAllAnime(): LiveData<List<AnimeEntity>>

    @Query("SELECT * FROM anime WHERE isFavorite = 1")
    fun getFavoriteAnime(): LiveData<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnime(anime: List<AnimeEntity>)

    @Update
    fun updateFavoriteAnime(anime: AnimeEntity)
}
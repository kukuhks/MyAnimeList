package com.ks.myanimelist.core.data.source.local.room

import androidx.room.*
import com.ks.myanimelist.core.data.source.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime")
    fun getAllAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime WHERE isFavorite = 1")
    fun getFavoriteAnime(): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: List<AnimeEntity>)

    @Update
    fun updateFavoriteAnime(anime: AnimeEntity)
}
package com.ks.myanimelist.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ks.myanimelist.core.data.source.local.entity.AnimeEntity


@Database(entities = [AnimeEntity::class], version = 3, exportSchema = false)
abstract class AnimeDatabase : RoomDatabase(){

    abstract fun animeDao(): AnimeDao

}
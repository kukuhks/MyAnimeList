package com.ks.myanimelist.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ks.myanimelist.core.data.source.local.entity.AnimeEntity


@Database(entities = [AnimeEntity::class], version = 3, exportSchema = false)
abstract class AnimeDatabase : RoomDatabase(){

    abstract fun animeDao(): AnimeDao

    companion object{
        @Volatile
        private var INSTANCE: AnimeDatabase? = null

        fun getInstance(context: Context): AnimeDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimeDatabase::class.java,
                    "Anime.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}
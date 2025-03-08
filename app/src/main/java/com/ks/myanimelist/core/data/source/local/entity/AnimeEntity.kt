package com.ks.myanimelist.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "anime")
data class AnimeEntity (
    @PrimaryKey
    val malId: Int,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "title_english")
    val title: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "synopsis")
    val synopsis: String,

    @ColumnInfo(name = "aired")
    val aired: String,

    @ColumnInfo(name = "score")
    val score: Double,

    @ColumnInfo(name = "studio")
    val studio: String,

    @ColumnInfo(name = "rating")
    val rating: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
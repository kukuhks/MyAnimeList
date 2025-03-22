package com.ks.myanimelist.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val malId: Int,
    val imageUrl: String,
    val title: String,
    val type: String,
    val synopsis: String,
    val episodes: Int,
    val score: Double,
    val year: Int,
    val rating: String,
    val isFavorite: Boolean
) : Parcelable
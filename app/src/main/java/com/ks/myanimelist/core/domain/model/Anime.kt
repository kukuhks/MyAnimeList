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
    val aired: String,
    val score: Double,
    val studios: List<String>,
    val rating: String,
    val isFavorite: Boolean
) : Parcelable
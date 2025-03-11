package com.ks.myanimelist.core.utils

import com.ks.myanimelist.core.data.source.local.entity.AnimeEntity
import com.ks.myanimelist.core.data.source.remote.response.AnimeResponse
import com.ks.myanimelist.core.domain.model.Anime

object DataMapper {
    fun mapResponseToEntities(input: List<AnimeResponse>): List<AnimeEntity> {
        val animeList = ArrayList<AnimeEntity>()
        input.map {
            val anime = AnimeEntity(
                malId = it.malId,
                imageUrl = it.images.jpg.imageUrl,
                title = it.title,
                type = it.type,
                episodes = it.episodes,
                score = it.score,
                year = it.year,
                synopsis = it.synopsis ?: "",
                rating = it.rating,
                isFavorite = false
            )
            animeList.add(anime)
        }
        return animeList
    }

    fun mapEntitiesToDomain(input: List<AnimeEntity>): List<Anime> =
        input.map {
            Anime(
                malId = it.malId,
                imageUrl = it.imageUrl,
                title = it.title,
                type = it.type,
                episodes = it.episodes,
                score = it.score,
                year = it.year,
                synopsis = it.synopsis,
                rating = it.rating,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainTpEntities(input: Anime) = AnimeEntity(
        malId = input.malId,
        imageUrl = input.imageUrl,
        title = input.title,
        type = input.type,
        episodes = input.episodes,
        score = input.score,
        year = input.year,
        synopsis = input.synopsis,
        rating = input.rating,
        isFavorite = input.isFavorite
    )
}
package org.andiez.core.utils

import org.andiez.core.local.entity.MovieDetailEntity
import org.andiez.core.local.entity.MovieEntity
import org.andiez.core.local.entity.TvShowDetailEntity
import org.andiez.core.local.entity.TvShowEntity
import org.andiez.core.remote.model.*

/**
 * Created by AndiezSatria on 14/10/2023.
 */
object DataCoreMapper {
    fun mapMovieResponsesToEntities(
        input: List<MovieResponse>,
    ): List<MovieEntity> = input.map { response ->
        MovieEntity(
            response.id,
            response.originalTitle,
            response.title,
            response.img ?: "",
            response.releaseDate ?: "",
            response.voteAverage,
        )
    }

    fun mapTvResponsesToEntities(
        input: List<TvShowResponse>,
    ): List<TvShowEntity> = input.map { response ->
        TvShowEntity(
            response.id,
            response.originalTitle,
            response.title,
            response.img ?: "",
            response.firstAired ?: "",
            response.voteAverage,
        )
    }


    private fun mapGenresResponseToEntities(input: List<GenreResponse>): String {
        var genres = ""
        for (i in input.indices) {
            genres += when (i) {
                0 -> input[i].name + (if (input.size != 1) "," else "")
                input.size - 1 -> " ${input[i].name}"
                else -> " ${input[i].name},"
            }
        }
        return genres
    }

    private fun mapSpokenLanguageResponseToEntity(input: List<SpokenLanguages>): String {
        var languages = ""
        for (i in input.indices) {
            languages += when (i) {
                0 -> input[i].name + (if (input.size != 1) "," else "")
                input.size - 1 -> " ${input[i].name}"
                else -> " ${input[i].name},"
            }
        }
        return languages
    }

    private fun mapRuntimesResponseToEntity(input: List<Int>): String {
        var runtimes = ""
        for (i in input.indices) {
            runtimes += when (i) {
                0 -> "${input[i]}" + (if (input.size != 1) "," else "")
                input.size - 1 -> " ${input[i]}"
                else -> " ${input[i]},"
            }
        }
        return runtimes
    }

    fun mapMovieDetailResponseToEntity(input: MovieDetailResponse): MovieDetailEntity =
        MovieDetailEntity(
            input.id,
            mapGenresResponseToEntities(input.genres),
            input.originalTitle,
            input.title,
            input.img ?: "",
            input.backdrop ?: "",
            input.releaseDate ?: "",
            input.voteAverage,
            input.overview ?: "",
            false,
            mapSpokenLanguageResponseToEntity(input.originalLanguage),
            input.runtime ?: 0,
            input.status,
            input.tagline
        )

    fun mapTvShowDetailResponseToEntity(input: TvShowDetailResponse): TvShowDetailEntity =
        TvShowDetailEntity(
            input.id,
            mapGenresResponseToEntities(input.genres),
            input.originalTitle,
            input.title,
            input.img ?: "",
            input.backdrop ?: "",
            input.releaseDate ?: "",
            input.voteAverage,
            input.overview ?: "",
            false,
            mapSpokenLanguageResponseToEntity(input.originalLanguage),
            mapRuntimesResponseToEntity(input.runtime),
            input.status,
            input.tagline
        )
}
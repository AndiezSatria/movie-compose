package org.andiez.domain.util

import org.andiez.core.local.entity.MovieDetailEntity
import org.andiez.core.local.entity.MovieEntity
import org.andiez.core.local.entity.MovieFavoriteEntity
import org.andiez.core.local.entity.TvShowDetailEntity
import org.andiez.core.local.entity.TvShowEntity
import org.andiez.core.local.entity.TvShowFavoriteEntity
import org.andiez.core.remote.model.CastResponse
import org.andiez.domain.model.Cast
import org.andiez.domain.model.Movie
import org.andiez.domain.model.MovieDetail
import org.andiez.domain.model.TvShow
import org.andiez.domain.model.TvShowDetail

/**
 * Created by AndiezSatria on 14/10/2023.
 */
object DomainDataMapper {
    fun mapMovieEntityToDomain(input: MovieEntity): Movie =
        Movie(
            input.id,
            input.originalTitle,
            input.title,
            input.img,
            input.releaseDate,
            input.voteAverage,
            isFavorite = input.isFavorite
        )

    fun mapTvEntityToDomain(input: TvShowEntity): TvShow =
        TvShow(
            input.id,
            input.originalTitle,
            input.title,
            input.img,
            input.firstAired,
            input.voteAverage,
            isFavorite = input.isFavorite
        )

    fun mapMovieFavoriteEntityToDomain(input: MovieFavoriteEntity): Movie = Movie(
        input.id,
        input.originalTitle,
        input.title,
        input.img,
        input.releaseDate,
        input.voteAverage,
        isFavorite = false,
    )

    fun mapTvShowFavoriteEntityToDomain(input: TvShowFavoriteEntity): TvShow = TvShow(
        input.id,
        input.originalTitle,
        input.title,
        input.img,
        input.firstAired,
        input.voteAverage,
        isFavorite = false,
    )

    fun mapTvShowToFavoriteEntity(input: TvShow): TvShowFavoriteEntity {
        return TvShowFavoriteEntity(
            input.id,
            input.originalTitle,
            input.title,
            input.img,
            input.firstAired,
            input.voteAverage,
        )
    }

    fun mapMovieToFavoriteEntity(input: Movie): MovieFavoriteEntity {
        return MovieFavoriteEntity(
            input.id,
            input.originalTitle,
            input.title,
            input.img,
            input.releaseDate,
            input.voteAverage,
        )
    }

    fun mapMovieDetailEntityToDomain(input: MovieDetailEntity): MovieDetail = MovieDetail(
        input.id,
        input.genres,
        input.originalTitle,
        input.title,
        input.img,
        input.backdrop,
        input.releaseDate,
        input.voteAverage,
        input.overview,
        input.isFavorite,
        input.originalLanguage,
        input.runtime,
        input.status,
        input.tagline,
    )

    fun mapTvShowDetailEntityToDomain(input: TvShowDetailEntity): TvShowDetail = TvShowDetail(
        input.id,
        input.genres,
        input.originalTitle,
        input.title,
        input.img,
        input.backdrop,
        input.releaseDate,
        input.voteAverage,
        input.overview,
        input.isFavorite,
        input.originalLanguage,
        input.runtimes,
        input.status,
        input.tagline,
    )

    fun mapCastResponsesToDomains(input: List<CastResponse>): List<Cast> = input.map { response ->
        Cast(
            response.id,
            response.character,
            response.name,
            response.originalName,
            response.img
        )
    }

}
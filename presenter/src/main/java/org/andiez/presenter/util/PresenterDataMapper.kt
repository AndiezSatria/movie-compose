package org.andiez.presenter.util

import org.andiez.domain.model.Movie
import org.andiez.domain.model.MovieDetail
import org.andiez.domain.model.TvShowDetail
import org.andiez.common.util.CommonUtils
import org.andiez.domain.model.Cast
import org.andiez.domain.model.TvShow
import org.andiez.presenter.model.CastItem
import org.andiez.presenter.model.DetailItem
import org.andiez.presenter.model.ShowItem

/**
 * Created by AndiezSatria on 14/10/2023.
 */
object PresenterDataMapper {
    fun mapMovieDomainsToPresenters(input: List<Movie>?): List<ShowItem> = input?.map { movie ->
        ShowItem(
            movie.id,
            movie.title,
            movie.img,
            movie.voteAverage,
        )
    } ?: emptyList()


    fun mapTvDomainsToPresenters(input: List<TvShow>?): List<ShowItem> = input?.map { tv ->
        ShowItem(
            tv.id,
            tv.title,
            tv.img,
            tv.voteAverage,
        )
    } ?: emptyList()

    fun mapMovieDomainToPresenter(input: Movie): ShowItem =
        ShowItem(
            input.id,
            input.title,
            input.img,
            input.voteAverage,
        )


    fun mapTvDomainToPresenter(input: TvShow): ShowItem =
        ShowItem(
            input.id,
            input.title,
            input.img,
            input.voteAverage,
        )

    fun mapCastDomainsToPresenters(input: List<Cast>): List<CastItem> = input.map {
        CastItem(it.id, it.character, it.name, it.img ?: "")
    }

    fun mapDetailMovieDomainToPresenter(input: MovieDetail): DetailItem = DetailItem(
        input.id,
        input.genres,
        input.originalTitle,
        input.title,
        input.img,
        input.backdrop,
        CommonUtils.convertFormatDate(input.releaseDate),
        input.voteAverage,
        input.overview ?: "Tidak ada Overview",
        input.isFavorite,
        input.originalLanguage,
        input.runtime.toString(),
        input.status,
        if (input.tagline != "") "\"${input.tagline}\"" else input.tagline
    )

    fun mapDetailTvShowDomainToPresenter(input: TvShowDetail): DetailItem = DetailItem(
        input.id,
        input.genres,
        input.originalTitle,
        input.title,
        input.img,
        input.backdrop,
        CommonUtils.convertFormatDate(input.releaseDate),
        input.voteAverage,
        input.overview,
        input.isFavorite,
        input.originalLanguage,
        input.runtimes,
        input.status,
        if (input.tagline != "") "\"${input.tagline}\"" else input.tagline
    )

    fun mapDetailPresenterToMovieDomain(input: DetailItem): Movie = Movie(
        id = input.id,
        originalTitle = input.originalTitle,
        title = input.title,
        img = input.img,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
    )

    fun mapDetailPresenterToTvShowDomain(input: DetailItem): TvShow = TvShow(
        id = input.id,
        originalTitle = input.originalTitle,
        title = input.title,
        img = input.img,
        firstAired = input.releaseDate,
        voteAverage = input.voteAverage,
    )
}
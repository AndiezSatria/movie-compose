package org.andiez.data.detail.repository.gateway

import kotlinx.coroutines.flow.Flow
import org.andiez.common.exception.Failure
import org.andiez.common.functional.Either
import org.andiez.core.local.entity.MovieDetailEntity
import org.andiez.core.local.entity.MovieFavoriteEntity
import org.andiez.core.local.entity.TvShowDetailEntity
import org.andiez.core.local.entity.TvShowFavoriteEntity
import org.andiez.core.remote.model.CastResponse

/**
 * Created by AndiezSatria on 15/10/2023.
 */
interface IDetailRepository {
    fun getMovieDetail(id: Int): Flow<Either<Failure, MovieDetailEntity>>
    fun getTvDetail(id: Int): Flow<Either<Failure, TvShowDetailEntity>>
    fun getCasts(type: String, id: Int): Flow<Either<Failure, List<CastResponse>>>

    suspend fun setMovieFavorite(movieFavoriteEntity: MovieFavoriteEntity)
    suspend fun deleteMovieFavorite(id: Int)

    suspend fun setTvFavorite(tvShowFavoriteEntity: TvShowFavoriteEntity)
    suspend fun deleteTvFavorite(id: Int)
}
package org.andiez.data.movie.repository.gateway

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.andiez.common.exception.Failure
import org.andiez.common.functional.Either

/**
 * Created by AndiezSatria on 17/04/2023.
 */
interface IAppRepository {
//    fun getDetailMovie(id: Int): Flow<Either<Failure, MovieDetail>>
//    fun getDetailTvShow(id: Int): Flow<Either<Failure, TvShowDetail>>
//    fun getCasts(type: String, id: Int): Flow<Either<Failure, List<Cast>>>
//
//    suspend fun setMovieFavorite(movie: Movie)
//    fun getMoviesFavorite(): Flow<PagingData<Movie>>
//
//    suspend fun setTvFavorite(tvShow: TvShow)
//    fun getTvShowsFavorite(): Flow<PagingData<TvShow>>
}
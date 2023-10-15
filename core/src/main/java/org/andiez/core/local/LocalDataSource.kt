package org.andiez.core.local

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import org.andiez.core.local.entity.MovieDetailEntity
import org.andiez.core.local.entity.MovieEntity
import org.andiez.core.local.entity.MovieFavoriteEntity
import org.andiez.core.local.entity.RemoteKeyMovieEntity
import org.andiez.core.local.entity.RemoteKeyTvEntity
import org.andiez.core.local.entity.TvShowDetailEntity
import org.andiez.core.local.entity.TvShowEntity
import org.andiez.core.local.entity.TvShowFavoriteEntity

/**
 * Created by AndiezSatria on 17/04/2023.
 */
interface LocalDataSource {
    fun getMovies(): PagingSource<Int, MovieEntity>
    fun getTvShows(): PagingSource<Int, TvShowEntity>
    fun getMovieDetail(id: Int): Flow<MovieDetailEntity>
    fun getTvDetail(id: Int): Flow<TvShowDetailEntity>
    fun getMoviesFavorite(): PagingSource<Int, MovieFavoriteEntity>
    fun getTvShowsFavorite(): PagingSource<Int, TvShowFavoriteEntity>

    suspend fun insertTvShows(tvShows: List<TvShowEntity>)
    suspend fun insertMovies(movies: List<MovieEntity>)
    suspend fun insertMovieDetail(movieDetail: MovieDetailEntity)
    suspend fun insertDetailTv(tvShowDetailEntity: TvShowDetailEntity)

    suspend fun addMovieFavorite(favoriteEntity: MovieFavoriteEntity)
    suspend fun addTvFavorite(favoriteEntity: TvShowFavoriteEntity)
    suspend fun deleteMovieFavorite(id: Int)
    suspend fun deleteTvFavorite(id: Int)

    suspend fun clearRemoteKeyMovie()
    suspend fun clearMovies()

    suspend fun getMovieRemote(id: Int): RemoteKeyMovieEntity?
    suspend fun insertMovieRemote(item: RemoteKeyMovieEntity)
    suspend fun deleteMovieRemote(id: Int)
    suspend fun getTvRemote(id: Int): RemoteKeyTvEntity?
    suspend fun insertTvRemote(item: RemoteKeyTvEntity)
    suspend fun deleteTvRemote(id: Int)
}
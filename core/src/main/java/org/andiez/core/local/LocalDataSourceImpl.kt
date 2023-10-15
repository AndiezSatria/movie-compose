package org.andiez.core.local

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.andiez.core.local.LocalDataSource
import org.andiez.core.local.dao.AppDao
import org.andiez.core.local.dao.RemoteKeyDao
import org.andiez.core.local.entity.MovieDetailEntity
import org.andiez.core.local.entity.MovieEntity
import org.andiez.core.local.entity.MovieFavoriteEntity
import org.andiez.core.local.entity.RemoteKeyMovieEntity
import org.andiez.core.local.entity.RemoteKeyTvEntity
import org.andiez.core.local.entity.TvShowDetailEntity
import org.andiez.core.local.entity.TvShowEntity
import org.andiez.core.local.entity.TvShowFavoriteEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val appDao: AppDao,
    private val remoteKeyDao: RemoteKeyDao
) : LocalDataSource {
    override fun getMovies(): PagingSource<Int, MovieEntity> {
        return appDao.getMovies()
    }

    override fun getTvShows(): PagingSource<Int, TvShowEntity> {
        return appDao.getTvShows()
    }

    override fun getMovieDetail(id: Int): Flow<MovieDetailEntity?> {
        val isFavorite = appDao.getMovieIsFavorite(id) > 0
        return appDao.getMovieDetail(id).map { it?.copy(isFavorite = isFavorite) }
    }

    override fun getTvDetail(id: Int): Flow<TvShowDetailEntity?> {
        val isFavorite = appDao.getTvIsFavorite(id) > 0
        return appDao.getTvDetail(id).map { it?.copy(isFavorite = isFavorite) }
    }

    override fun getMoviesFavorite(): PagingSource<Int, MovieFavoriteEntity> {
        return appDao.getMoviesFavorite()
    }

    override fun getTvShowsFavorite(): PagingSource<Int, TvShowFavoriteEntity> {
        return appDao.getTvShowsFavorite()
    }

    override suspend fun insertTvShows(tvShows: List<TvShowEntity>) {
        appDao.insertTvShows(tvShows)
    }

    override suspend fun insertMovies(movies: List<MovieEntity>) {
        appDao.insertMovies(movies)
    }

    override suspend fun insertMovieDetail(movieDetail: MovieDetailEntity) {
        appDao.insertDetailMovie(movieDetail)
    }

    override suspend fun insertDetailTv(tvShowDetailEntity: TvShowDetailEntity) {
        appDao.insertDetailTv(tvShowDetailEntity)
    }

    override suspend fun addMovieFavorite(favoriteEntity: MovieFavoriteEntity) {
        appDao.addMovieFavorite(favoriteEntity)
    }

    override suspend fun addTvFavorite(favoriteEntity: TvShowFavoriteEntity) {
        appDao.addTvFavorite(favoriteEntity)
    }

    override suspend fun deleteMovieFavorite(id: Int) {
        appDao.deleteMovieFavorite(id)
    }

    override suspend fun deleteTvFavorite(id: Int) {
        appDao.deleteTvFavorite(id)
    }

    override suspend fun clearRemoteKeyMovie() {
        remoteKeyDao.clearRemoteMovieKeys()
    }

    override suspend fun clearMovies() {
        appDao.clearAllMovies()
    }

    override suspend fun getMovieRemote(id: Int): RemoteKeyMovieEntity? {
        return remoteKeyDao.getMovieKeyById(id)
    }

    override suspend fun insertMovieRemote(item: RemoteKeyMovieEntity) {
        remoteKeyDao.insertMovieKey(item)
    }

    override suspend fun deleteMovieRemote(id: Int) {
        remoteKeyDao.deleteMovieKeyById(id)
    }

    override suspend fun getTvRemote(id: Int): RemoteKeyTvEntity? {
        return remoteKeyDao.getTvKeyById(id)
    }

    override suspend fun insertTvRemote(item: RemoteKeyTvEntity) {
        remoteKeyDao.insertTvKey(item)
    }

    override suspend fun deleteTvRemote(id: Int) {
        remoteKeyDao.deleteTvKeyById(id)
    }
}
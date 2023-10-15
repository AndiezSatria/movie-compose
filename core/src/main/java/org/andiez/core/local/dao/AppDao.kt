package org.andiez.core.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.andiez.core.local.entity.MovieDetailEntity
import org.andiez.core.local.entity.MovieEntity
import org.andiez.core.local.entity.MovieFavoriteEntity
import org.andiez.core.local.entity.TvShowDetailEntity
import org.andiez.core.local.entity.TvShowEntity
import org.andiez.core.local.entity.TvShowFavoriteEntity

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Dao
interface AppDao {
    @Query("SELECT * FROM MovieTable Order By page")
    fun getMovies(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM TvShowTable Order By page")
    fun getTvShows(): PagingSource<Int, TvShowEntity>

    @Query("SELECT * FROM MovieDetailTable WHERE id = :id")
    fun getMovieDetail(id: Int): Flow<MovieDetailEntity?>

    @Query("SELECT * FROM TvShowDetailTable WHERE id = :id")
    fun getTvDetail(id: Int): Flow<TvShowDetailEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShow: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(detailMovieEntity: MovieDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailTv(detailTvShowEntity: TvShowDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieFavorite(favoriteEntity: MovieFavoriteEntity)

    @Query("DELETE FROM MovieFavoriteTable WHERE id = :id")
    suspend fun deleteMovieFavorite(id: Int)

    @Query("SELECT * FROM MovieFavoriteTable")
    fun getMoviesFavorite(): PagingSource<Int, MovieFavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTvFavorite(favoriteEntity: TvShowFavoriteEntity)

    @Query("DELETE FROM TvShowFavoriteTable WHERE id = :id")
    suspend fun deleteTvFavorite(id: Int)

    @Query("SELECT * FROM TvShowFavoriteTable")
    fun getTvShowsFavorite(): PagingSource<Int, TvShowFavoriteEntity>

    @Query("DELETE FROM MovieTable")
    suspend fun clearAllMovies()

    @Query("DELETE FROM TvShowTable")
    suspend fun clearAllTvShows()

    @Query("SELECT COUNT(*) FROM TvShowFavoriteTable WHERE id = :id")
    fun getTvIsFavorite(id: Int): Int

    @Query("SELECT COUNT(*) FROM MovieFavoriteTable WHERE id = :id")
    fun getMovieIsFavorite(id: Int): Int
}
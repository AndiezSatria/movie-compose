package org.andiez.data.movie.repository

import androidx.paging.Pager
import org.andiez.core.local.LocalDataSource
import org.andiez.core.local.db.AppDatabase
import org.andiez.core.local.entity.MovieEntity
import org.andiez.core.local.entity.MovieFavoriteEntity
import org.andiez.core.local.entity.TvShowEntity
import org.andiez.core.local.entity.TvShowFavoriteEntity
import org.andiez.core.remote.RemoteDataSource
import org.andiez.core.remote.service.AppService
import javax.inject.Inject

/**
 * Created by AndiezSatria on 17/04/2023.
 */


class AppRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val moviePager: Pager<Int, MovieEntity>,
    private val tvPager: Pager<Int, TvShowEntity>,
    private val movieFavoritePager: Pager<Int, MovieFavoriteEntity>,
    private val tvFavoritePager: Pager<Int, TvShowFavoriteEntity>,
    private val appDatabase: AppDatabase,
    private val appService: AppService,
)
//    : IAppRepository
{

//    override fun getDetailMovie(id: Int): Flow<Either<Failure, MovieDetail>> =
//        object : NetworkBoundResource<MovieDetail, MovieDetailResponse>() {
//
//            override suspend fun loadFromDB(): Flow<MovieDetail?> {
//                return localDataSource.getMovieDetail(id)
//                    .map { DataMapper.mapMovieDetailEntityToDomain(it) }
//            }
//
//            override fun shouldFetch(data: MovieDetail?): Boolean = data == null
//
//            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> {
//                return remoteDataSource.getDetailMovie(id)
//            }
//
//            override suspend fun saveCallResult(data: MovieDetailResponse) {
//                localDataSource.insertMovieDetail(DataMapper.mapMovieDetailResponseToEntity(data))
//            }
//        }.asFlow()
//
//    override fun getDetailTvShow(id: Int): Flow<Either<Failure, TvShowDetail>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getCasts(type: String, id: Int): Flow<Either<Failure, List<Cast>>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun setMovieFavorite(movie: Movie) {
//        localDataSource.addMovieFavorite(DataMapper.mapMovieToEntity(movie))
//    }
//
//    override fun getMoviesFavorite(): Flow<PagingData<Movie>> {
//        return movieFavoritePager.flow.map { pagingData ->
//            pagingData.map { DataMapper.mapMovieFavoriteEntityToDomain(it) }
//        }
//    }
//
//    override suspend fun setTvFavorite(tvShow: TvShow) {
//        localDataSource.addTvFavorite(DataMapper.mapTvShowToEntity(tvShow))
//    }
//
//    override fun getTvShowsFavorite(): Flow<PagingData<TvShow>> {
//        return tvFavoritePager.flow.map { pagingData ->
//            pagingData.map { DataMapper.mapTvShowFavoriteEntityToDomain(it) }
//        }
//    }
}
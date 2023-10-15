package org.andiez.data.detail.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.andiez.common.exception.Failure
import org.andiez.common.functional.Either
import org.andiez.common.network.NetworkChecker
import org.andiez.core.local.LocalDataSource
import org.andiez.core.local.entity.MovieDetailEntity
import org.andiez.core.local.entity.MovieFavoriteEntity
import org.andiez.core.local.entity.TvShowDetailEntity
import org.andiez.core.local.entity.TvShowFavoriteEntity
import org.andiez.core.remote.RemoteDataSource
import org.andiez.core.remote.model.ApiResponse
import org.andiez.core.remote.model.CastResponse
import org.andiez.core.remote.model.MovieDetailResponse
import org.andiez.core.remote.model.TvShowDetailResponse
import org.andiez.core.source.NetworkBoundResource
import org.andiez.core.source.NetworkOnlyResource
import org.andiez.core.utils.DataCoreMapper
import org.andiez.data.detail.repository.gateway.IDetailRepository
import javax.inject.Inject

/**
 * Created by AndiezSatria on 15/10/2023.
 */

class DetailRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val networkChecker: NetworkChecker,
) : IDetailRepository {
    override fun getMovieDetail(id: Int): Flow<Either<Failure, MovieDetailEntity>> =
        object : NetworkBoundResource<MovieDetailEntity, MovieDetailResponse>() {
            override suspend fun loadFromDB(): Flow<MovieDetailEntity?> {
                return localDataSource.getMovieDetail(id)
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> {
                return remoteDataSource.getDetailMovie(id)
            }

            override suspend fun saveCallResult(data: MovieDetailResponse) {
                localDataSource.insertMovieDetail(DataCoreMapper.mapMovieDetailResponseToEntity(data))
            }

            override fun shouldFetch(data: MovieDetailEntity?): Boolean = data == null

        }.asFlow()

    override fun getTvDetail(id: Int): Flow<Either<Failure, TvShowDetailEntity>> =
        object : NetworkBoundResource<TvShowDetailEntity, TvShowDetailResponse>() {
            override suspend fun loadFromDB(): Flow<TvShowDetailEntity?> {
                return localDataSource.getTvDetail(id)
            }

            override suspend fun createCall(): Flow<ApiResponse<TvShowDetailResponse>> {
                return remoteDataSource.getDetailTvShow(id)
            }

            override suspend fun saveCallResult(data: TvShowDetailResponse) {
                localDataSource.insertDetailTv(DataCoreMapper.mapTvShowDetailResponseToEntity(data))
            }

            override fun shouldFetch(data: TvShowDetailEntity?): Boolean = data == null

        }.asFlow()

    override fun getCasts(type: String, id: Int): Flow<Either<Failure, List<CastResponse>>> =
        object : NetworkOnlyResource<List<CastResponse>, List<CastResponse>>(networkChecker) {
            override fun loadFromNetwork(data: List<CastResponse>): Flow<List<CastResponse>?> {
                return flowOf(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<CastResponse>>> {
                return remoteDataSource.getCasts(type, id)
            }

        }.asFlow()

    override suspend fun setMovieFavorite(movieFavoriteEntity: MovieFavoriteEntity) {
        localDataSource.addMovieFavorite(movieFavoriteEntity)
    }

    override suspend fun deleteMovieFavorite(id: Int) {
        localDataSource.deleteMovieFavorite(id)
    }

    override suspend fun setTvFavorite(tvShowFavoriteEntity: TvShowFavoriteEntity) {
        localDataSource.addTvFavorite(tvShowFavoriteEntity)
    }

    override suspend fun deleteTvFavorite(id: Int) {
        localDataSource.deleteTvFavorite(id)
    }
}
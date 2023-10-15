package org.andiez.core.remote

import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.andiez.core.remote.model.ApiResponse
import org.andiez.core.remote.model.CastResponse
import org.andiez.core.remote.model.MovieDetailResponse
import org.andiez.core.remote.model.MovieResponse
import org.andiez.core.remote.model.TvShowDetailResponse
import org.andiez.core.remote.model.TvShowResponse
import org.andiez.core.remote.service.AppService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Singleton
class RemoteDataSourceImpl @Inject constructor(private val apiService: AppService) :
    RemoteDataSource {
    override suspend fun getMovies(page: Int): Flow<ApiResponse<List<MovieResponse>>> = flow {
        try {
            val response = apiService.getMovie(page = page)
            val dataArray = response.results
            emit(ApiResponse.Success(dataArray))

        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            e { "RemoteDataSource ${e.message}" }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getTvShows(page: Int): Flow<ApiResponse<List<TvShowResponse>>> = flow {
        try {
            val response = apiService.getTvShow(page = page)
            val dataArray = response.results
            emit(ApiResponse.Success(dataArray))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            e { "RemoteDataSource ${e.message}" }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getDetailMovie(id: Int): Flow<ApiResponse<MovieDetailResponse>> =
        flow<ApiResponse<MovieDetailResponse>> {
            val response = apiService.getDetailMovie(id)
            d { "${response.id} ${response.title}" }
            emit(ApiResponse.Success(response))
        }.catch {
            emit(ApiResponse.Error(it.message.toString()))
            e { "RemoteDataSource ${it.message}" }
        }.flowOn(Dispatchers.IO)

    override suspend fun getDetailTvShow(id: Int): Flow<ApiResponse<TvShowDetailResponse>> =
        flow<ApiResponse<TvShowDetailResponse>> {
            val response = apiService.getTvDetail(id)
            emit(ApiResponse.Success(response))

        }.catch {
            emit(ApiResponse.Error(it.message.toString()))
            e { "RemoteDataSource ${it.message}" }
        }.flowOn(Dispatchers.IO).flowOn(Dispatchers.IO)

    override suspend fun getCasts(type: String, id: Int): Flow<ApiResponse<List<CastResponse>>> =
        flow {
            try {
                val response = apiService.getCastMovie(type, id)
                val dataArray = response.casts
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.casts))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                e { "RemoteDataSource ${e.message}" }
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getSearchedMovies(query: String): Flow<ApiResponse<List<MovieResponse>>> =
        flow {
            try {
                val response = apiService.getSearchedMovies(query = query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                e { "RemoteDataSource ${e.message}" }
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getSearchedTvShows(query: String): Flow<ApiResponse<List<TvShowResponse>>> =
        flow {
            try {
                val response = apiService.getSearchedTvShows(query = query)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                e { "RemoteDataSource ${e.message}" }
            }
        }.flowOn(Dispatchers.IO)

}
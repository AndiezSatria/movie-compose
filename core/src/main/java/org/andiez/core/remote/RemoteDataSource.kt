package org.andiez.core.remote

import kotlinx.coroutines.flow.Flow
import org.andiez.core.remote.model.ApiResponse
import org.andiez.core.remote.model.CastResponse
import org.andiez.core.remote.model.MovieDetailResponse
import org.andiez.core.remote.model.MovieResponse
import org.andiez.core.remote.model.TvShowDetailResponse
import org.andiez.core.remote.model.TvShowResponse

/**
 * Created by AndiezSatria on 17/04/2023.
 */

interface RemoteDataSource {
    suspend fun getMovies(page: Int): Flow<ApiResponse<List<MovieResponse>>>
    suspend fun getTvShows(page: Int): Flow<ApiResponse<List<TvShowResponse>>>
    suspend fun getDetailMovie(id: Int): Flow<ApiResponse<MovieDetailResponse>>
    suspend fun getDetailTvShow(id: Int): Flow<ApiResponse<TvShowDetailResponse>>
    suspend fun getCasts(type: String, id: Int): Flow<ApiResponse<List<CastResponse>>>
    suspend fun getSearchedMovies(query: String): Flow<ApiResponse<List<MovieResponse>>>
    suspend fun getSearchedTvShows(query: String): Flow<ApiResponse<List<TvShowResponse>>>
}
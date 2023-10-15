package org.andiez.core.remote.service

import org.andiez.core.remote.model.ListCastResponse
import org.andiez.core.remote.model.ListMovieResponse
import org.andiez.core.remote.model.ListTvResponse
import org.andiez.core.remote.model.MovieDetailResponse
import org.andiez.core.remote.model.TvShowDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by AndiezSatria on 17/04/2023.
 */

interface AppService {
    @GET("movie/now_playing")
    suspend fun getMovie(
        @Query("page") page: Int = 1
    ): ListMovieResponse

    @GET("tv/airing_today")
    suspend fun getTvShow(
        @Query("page") page: Int = 1
    ): ListTvResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") id: Int,
    ): MovieDetailResponse

    @GET("{type}/{movie_id}/credits")
    suspend fun getCastMovie(
        @Path("type") showType: String,
        @Path("movie_id") id: Int,
    ): ListCastResponse

    @GET("tv/{tv_id}")
    suspend fun getTvDetail(
        @Path("tv_id") id: Int,
    ): TvShowDetailResponse

    @GET("search/movie")
    suspend fun getSearchedMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): ListMovieResponse

    @GET("search/tv")
    suspend fun getSearchedTvShows(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): ListTvResponse
}
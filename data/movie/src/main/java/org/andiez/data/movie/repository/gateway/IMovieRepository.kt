package org.andiez.data.movie.repository.gateway

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.andiez.core.local.entity.MovieEntity

/**
 * Created by AndiezSatria on 14/10/2023.
 */
interface IMovieRepository {
    fun getMovies(): Flow<PagingData<MovieEntity>>
    fun getSearchedMovies(query: String): Flow<PagingData<MovieEntity>>
}
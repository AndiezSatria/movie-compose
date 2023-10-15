package org.andiez.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.andiez.data.movie.repository.gateway.IMovieRepository
import org.andiez.data.movie.util.MovieDataMapper
import org.andiez.domain.model.Movie
import org.andiez.domain.util.DomainDataMapper
import javax.inject.Inject

/**
 * Created by AndiezSatria on 14/10/2023.
 */

class MovieUseCase @Inject constructor(private val movieRepository: IMovieRepository) {
    operator fun invoke(query: String = ""): Flow<PagingData<Movie>> {
        return if (query.isEmpty()) {
            movieRepository.getMovies().map { pagingData ->
                pagingData.map { DomainDataMapper.mapMovieEntityToDomain(it) }
            }
        } else {
            movieRepository.getSearchedMovies(query).map { pagingData ->
                pagingData.map { DomainDataMapper.mapMovieEntityToDomain(it) }
            }
        }.flowOn(Dispatchers.IO)
    }
}
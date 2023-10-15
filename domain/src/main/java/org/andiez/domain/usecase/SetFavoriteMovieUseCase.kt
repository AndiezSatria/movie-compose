package org.andiez.domain.usecase

import org.andiez.data.detail.repository.gateway.IDetailRepository
import org.andiez.domain.model.Movie
import org.andiez.domain.util.DomainDataMapper
import javax.inject.Inject

/**
 * Created by AndiezSatria on 15/10/2023.
 */
class SetFavoriteMovieUseCase @Inject constructor(private val detailRepository: IDetailRepository) {
    suspend operator fun invoke(movie: Movie, isFavorite: Boolean) {
        if (!isFavorite)
            detailRepository.setMovieFavorite(DomainDataMapper.mapMovieToFavoriteEntity(movie))
        else
            detailRepository.deleteMovieFavorite(movie.id)
    }
}
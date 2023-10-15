package org.andiez.domain.usecase

import org.andiez.data.detail.repository.gateway.IDetailRepository
import org.andiez.domain.model.TvShow
import org.andiez.domain.util.DomainDataMapper
import javax.inject.Inject

/**
 * Created by AndiezSatria on 15/10/2023.
 */
class SetFavoriteTvShowUseCase @Inject constructor(private val detailRepository: IDetailRepository) {
    suspend operator fun invoke(tvShow: TvShow, isFavorite: Boolean) {
        if (!isFavorite)
            detailRepository.setTvFavorite(DomainDataMapper.mapTvShowToFavoriteEntity(tvShow))
        else
            detailRepository.deleteTvFavorite(tvShow.id)
    }
}
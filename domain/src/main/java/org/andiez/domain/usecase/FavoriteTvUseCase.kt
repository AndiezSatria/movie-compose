package org.andiez.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.andiez.data.favorite.repository.gateway.IFavoriteRepository
import org.andiez.domain.model.TvShow
import org.andiez.domain.util.DomainDataMapper
import javax.inject.Inject

/**
 * Created by AndiezSatria on 15/10/2023.
 */
class FavoriteTvUseCase @Inject constructor(private val favoriteRepository: IFavoriteRepository) {
    operator fun invoke(): Flow<PagingData<TvShow>> {
        return favoriteRepository.getTvShowFavorite().map { pagingData ->
            pagingData.map { DomainDataMapper.mapTvShowFavoriteEntityToDomain(it) }
        }
    }
}
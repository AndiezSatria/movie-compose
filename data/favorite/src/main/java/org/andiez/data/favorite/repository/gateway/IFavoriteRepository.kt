package org.andiez.data.favorite.repository.gateway

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.andiez.core.local.entity.MovieFavoriteEntity
import org.andiez.core.local.entity.TvShowFavoriteEntity

/**
 * Created by AndiezSatria on 15/10/2023.
 */
interface IFavoriteRepository {
    fun getMovieFavorite(): Flow<PagingData<MovieFavoriteEntity>>
    fun getTvShowFavorite(): Flow<PagingData<TvShowFavoriteEntity>>
}
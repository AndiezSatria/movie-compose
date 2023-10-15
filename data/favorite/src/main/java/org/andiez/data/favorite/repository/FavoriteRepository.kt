package org.andiez.data.favorite.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.andiez.core.local.entity.MovieFavoriteEntity
import org.andiez.core.local.entity.TvShowFavoriteEntity
import org.andiez.data.favorite.repository.gateway.IFavoriteRepository
import javax.inject.Inject

/**
 * Created by AndiezSatria on 15/10/2023.
 */
class FavoriteRepository @Inject constructor(
    private val tvShowFavoritePager: Pager<Int, TvShowFavoriteEntity>,
    private val movieFavoritePager: Pager<Int, MovieFavoriteEntity>,
): IFavoriteRepository {
    override fun getMovieFavorite(): Flow<PagingData<MovieFavoriteEntity>> {
        return movieFavoritePager.flow
    }

    override fun getTvShowFavorite(): Flow<PagingData<TvShowFavoriteEntity>> {
        return tvShowFavoritePager.flow
    }

}
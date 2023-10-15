package org.andiez.data.tvshow.repository.gateway

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.andiez.core.local.entity.TvShowEntity

/**
 * Created by AndiezSatria on 14/10/2023.
 */
interface ITvShowRepository {
    fun getTvShows(): Flow<PagingData<TvShowEntity>>
    fun getSearchedTvShows(query: String): Flow<PagingData<TvShowEntity>>
}
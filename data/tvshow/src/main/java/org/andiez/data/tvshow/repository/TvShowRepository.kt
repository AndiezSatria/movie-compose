package org.andiez.data.tvshow.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.andiez.core.local.LocalDataSource
import org.andiez.core.local.entity.TvShowEntity
import org.andiez.core.local.db.AppDatabase
import org.andiez.core.mediator.TvSearchRemoteMediator
import org.andiez.core.remote.service.AppService
import org.andiez.data.tvshow.repository.gateway.ITvShowRepository
import org.andiez.data.tvshow.util.TvShowDataMapper
import javax.inject.Inject

/**
 * Created by AndiezSatria on 14/10/2023.
 */
class TvShowRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val tvShowPager: Pager<Int, TvShowEntity>,
    private val appDatabase: AppDatabase,
    private val appService: AppService,
): ITvShowRepository {
    override fun getTvShows(): Flow<PagingData<TvShowEntity>> {
        return tvShowPager.flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getSearchedTvShows(query: String): Flow<PagingData<TvShowEntity>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                initialLoadSize = 20,
            ),
            remoteMediator = TvSearchRemoteMediator(
                appDatabase = appDatabase,
                apiService = appService,
                query = query,
            ),
            pagingSourceFactory = {
                localDataSource.getTvShows()
            },
        )
        return pager.flow
    }
}
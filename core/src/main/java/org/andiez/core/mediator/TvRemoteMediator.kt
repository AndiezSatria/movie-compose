package org.andiez.core.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.github.ajalt.timberkt.d
import org.andiez.core.local.db.AppDatabase
import org.andiez.core.local.entity.RemoteKeyTvEntity
import org.andiez.core.local.entity.TvShowEntity
import org.andiez.core.remote.service.AppService
import org.andiez.core.utils.DataCoreMapper
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by AndiezSatria on 13/10/2023.
 */

@OptIn(ExperimentalPagingApi::class)
class TvRemoteMediator @Inject constructor(
    private val appDatabase: AppDatabase,
    private val apiService: AppService,
): RemoteMediator<Int, TvShowEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TvShowEntity>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                d { "Next Key Tv : $nextKey" }
                nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }
        try {

            val apiResponse = apiService.getTvShow(page = page)
            val tvShows = apiResponse.results
            d { "Size : ${tvShows.size}" }
            val endOfPaginationReached = tvShows.isEmpty()
            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.remoteKeyDao().clearRemoteTvKeys()
                    appDatabase.appDao().clearAllTvShows()
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = tvShows.map {
                    RemoteKeyTvEntity(
                        id = it.id,
                        prevKey = prevKey,
                        currentPage = page,
                        nextKey = nextKey
                    )
                }

                appDatabase.remoteKeyDao().insertAllTvKey(remoteKeys)
                appDatabase.appDao().insertTvShows(
                    DataCoreMapper.mapTvResponsesToEntities(tvShows).onEach { it.page = page })
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, TvShowEntity>): RemoteKeyTvEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                appDatabase.remoteKeyDao().getTvKeyById(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, TvShowEntity>): RemoteKeyTvEntity? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { movie ->
            appDatabase.remoteKeyDao().getTvKeyById(movie.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, TvShowEntity>): RemoteKeyTvEntity? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { movie ->
            appDatabase.remoteKeyDao().getTvKeyById(movie.id)
        }
    }
}
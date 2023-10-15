package org.andiez.data.movie.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.andiez.core.local.LocalDataSource
import org.andiez.core.local.entity.MovieEntity
import org.andiez.core.local.db.AppDatabase
import org.andiez.core.mediator.MovieSearchRemoteMediator
import org.andiez.core.remote.service.AppService
import org.andiez.data.movie.repository.gateway.IMovieRepository
import javax.inject.Inject

/**
 * Created by AndiezSatria on 14/10/2023.
 */

class MovieRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val moviePager: Pager<Int, MovieEntity>,
    private val appDatabase: AppDatabase,
    private val appService: AppService,
) : IMovieRepository {
    override fun getMovies(): Flow<PagingData<MovieEntity>> {
        return moviePager.flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getSearchedMovies(query: String): Flow<PagingData<MovieEntity>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                initialLoadSize = 20,
            ),
            remoteMediator = MovieSearchRemoteMediator(
                appDatabase = appDatabase,
                apiService = appService,
                query = query,
            ),
            pagingSourceFactory = {
                localDataSource.getMovies()
            },
        )
        return pager.flow
    }

}

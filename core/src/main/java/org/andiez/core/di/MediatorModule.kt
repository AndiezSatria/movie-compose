package org.andiez.core.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.andiez.core.local.entity.MovieEntity
import org.andiez.core.local.entity.TvShowEntity
import org.andiez.core.local.db.AppDatabase
import org.andiez.core.local.entity.MovieFavoriteEntity
import org.andiez.core.local.entity.TvShowFavoriteEntity
import org.andiez.core.mediator.MovieRemoteMediator
import org.andiez.core.mediator.TvRemoteMediator
import org.andiez.core.remote.service.AppService
import javax.inject.Singleton

/**
 * Created by AndiezSatria on 14/10/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
class MediatorModule {

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideMoviePager(
        appDatabase: AppDatabase,
        appService: AppService,
    ): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                initialLoadSize = 20,
            ),
            pagingSourceFactory = {
                appDatabase.appDao().getMovies()
            },
            remoteMediator = MovieRemoteMediator(
                appDatabase = appDatabase,
                apiService = appService,
            )
        )
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideTvShowPager(
        appDatabase: AppDatabase,
        appService: AppService,
    ): Pager<Int, TvShowEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                initialLoadSize = 20,
            ),
            pagingSourceFactory = {
                appDatabase.appDao().getTvShows()
            },
            remoteMediator = TvRemoteMediator(
                appDatabase = appDatabase,
                apiService = appService,
            )
        )
    }

    @Provides
    @Singleton
    fun provideFavoriteTvShowPager(
        appDatabase: AppDatabase,
    ): Pager<Int, TvShowFavoriteEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                initialLoadSize = 20,
            ),
            pagingSourceFactory = {
                appDatabase.appDao().getTvShowsFavorite()
            },
        )
    }

    @Provides
    @Singleton
    fun provideFavoriteMoviePager(
        appDatabase: AppDatabase,
    ): Pager<Int, MovieFavoriteEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                initialLoadSize = 20,
            ),
            pagingSourceFactory = {
                appDatabase.appDao().getMoviesFavorite()
            },
        )
    }
}
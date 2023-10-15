package org.andiez.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.andiez.data.detail.repository.DetailRepository
import org.andiez.data.detail.repository.gateway.IDetailRepository
import org.andiez.data.favorite.repository.FavoriteRepository
import org.andiez.data.favorite.repository.gateway.IFavoriteRepository
import org.andiez.data.movie.repository.MovieRepository
import org.andiez.data.movie.repository.gateway.IMovieRepository
import org.andiez.data.tvshow.repository.gateway.ITvShowRepository
import org.andiez.data.tvshow.repository.TvShowRepository
import javax.inject.Singleton

/**
 * Created by AndiezSatria on 14/10/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    @Singleton
    abstract fun provideMovieRepository(movieRepository: MovieRepository): IMovieRepository

    @Binds
    @Singleton
    abstract fun provideTvShowRepository(tvShowRepository: TvShowRepository): ITvShowRepository

    @Binds
    @Singleton
    abstract fun provideFavoriteRepository(favoriteRepository: FavoriteRepository): IFavoriteRepository

    @Binds
    @Singleton
    abstract fun provideDetailRepository(detailRepository: DetailRepository): IDetailRepository
}
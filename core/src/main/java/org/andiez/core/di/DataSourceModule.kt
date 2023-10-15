package org.andiez.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.andiez.core.local.LocalDataSource
import org.andiez.core.local.LocalDataSourceImpl
import org.andiez.core.remote.RemoteDataSource
import org.andiez.core.remote.RemoteDataSourceImpl

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Module(includes = [DatabaseModule::class, RemoteModule::class])
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}
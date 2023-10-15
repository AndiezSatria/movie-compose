package org.andiez.data.detail.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.andiez.common.network.NetworkChecker
import org.andiez.common.network.NetworkCheckerImpl
import javax.inject.Singleton

/**
 * Created by AndiezSatria on 15/10/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailModule {
    @Binds
    @Singleton
    abstract fun provideNetworkChecker(networkCheckerImpl: NetworkCheckerImpl): NetworkChecker
}
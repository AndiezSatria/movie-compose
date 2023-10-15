package org.andiez.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.andiez.core.local.dao.AppDao
import org.andiez.core.local.dao.RemoteKeyDao
import org.andiez.core.local.db.AppDatabase
import javax.inject.Singleton

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "BaseCompose.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideDao(database: AppDatabase): AppDao = database.appDao()

    @Provides
    fun provideRemoteKeyDao(database: AppDatabase): RemoteKeyDao = database.remoteKeyDao()
}
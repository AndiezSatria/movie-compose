package org.andiez.core.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.andiez.core.local.dao.AppDao
import org.andiez.core.local.dao.RemoteKeyDao
import org.andiez.core.local.entity.MovieDetailEntity
import org.andiez.core.local.entity.MovieEntity
import org.andiez.core.local.entity.MovieFavoriteEntity
import org.andiez.core.local.entity.RemoteKeyMovieEntity
import org.andiez.core.local.entity.RemoteKeyTvEntity
import org.andiez.core.local.entity.TvShowDetailEntity
import org.andiez.core.local.entity.TvShowEntity
import org.andiez.core.local.entity.TvShowFavoriteEntity

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Database(
    entities = [
        MovieEntity::class,
        TvShowEntity::class,
        MovieFavoriteEntity::class,
        TvShowFavoriteEntity::class,
        TvShowDetailEntity::class,
        MovieDetailEntity::class,
        RemoteKeyMovieEntity::class,
        RemoteKeyTvEntity::class,
    ],
    version = 4,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}
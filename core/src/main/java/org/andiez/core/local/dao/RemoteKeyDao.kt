package org.andiez.core.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.andiez.core.local.entity.RemoteKeyMovieEntity
import org.andiez.core.local.entity.RemoteKeyTvEntity

/**
 * Created by AndiezSatria on 12/10/2023.
 */
@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieKey(item: RemoteKeyMovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovieKey(item: List<RemoteKeyMovieEntity>)

    @Query("SELECT * FROM RemoteKeyMovieTable WHERE id = :id")
    suspend fun getMovieKeyById(id: Int): RemoteKeyMovieEntity?

    @Query("DELETE FROM RemoteKeyMovieTable WHERE id = :id")
    suspend fun deleteMovieKeyById(id: Int)

    @Query("DELETE From RemoteKeyMovieTable")
    suspend fun clearRemoteMovieKeys()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvKey(item: RemoteKeyTvEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTvKey(item: List<RemoteKeyTvEntity>)

    @Query("SELECT * FROM RemoteKeyTvTable WHERE id = :id")
    suspend fun getTvKeyById(id: Int): RemoteKeyTvEntity?

    @Query("DELETE FROM RemoteKeyTvTable WHERE id = :id")
    suspend fun deleteTvKeyById(id: Int)

    @Query("DELETE FROM RemoteKeyTvTable")
    suspend fun clearRemoteTvKeys()

    @Query("SELECT created_at From RemoteKeyMovieTable Order By created_at DESC LIMIT 1")
    suspend fun getMovieCreationTime(): Long?

    @Query("Select created_at From RemoteKeyMovieTable Order By created_at DESC LIMIT 1")
    suspend fun getTvCreationTime(): Long?
}
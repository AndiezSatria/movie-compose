package org.andiez.core.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by AndiezSatria on 12/10/2023.
 */

@Entity(tableName = "RemoteKeyMovieTable")
data class RemoteKeyMovieEntity(
    @PrimaryKey val id: Int,
    val prevKey: Int?,
    val currentPage: Int,
    val nextKey: Int?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)


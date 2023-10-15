package org.andiez.core.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by AndiezSatria on 13/10/2023.
 */

@Entity(tableName = "TvShowFavoriteTable")
data class TvShowFavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "originalTitle")
    var originalTitle: String = "",
    @ColumnInfo(name = "title")
    var title: String = "",
    @ColumnInfo(name = "img")
    var img: String = "",
    @ColumnInfo(name = "releaseDate")
    var firstAired: String = "",
    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double = 0.0,
)

package org.andiez.core.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by AndiezSatria on 12/10/2023.
 */

data class MovieResponse(
    @field:SerializedName("id")
    var id: Int,
    @field:SerializedName("original_title")
    var originalTitle: String,
    @field:SerializedName("title")
    var title: String,
    @field:SerializedName("poster_path")
    var img: String?,
    @field:SerializedName("release_date")
    var releaseDate: String? = "",
    @field:SerializedName("vote_average")
    var voteAverage: Double
)
package org.andiez.core.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by AndiezSatria on 12/10/2023.
 */
data class ListMovieResponse(
    @field:SerializedName("results")
    var results: List<MovieResponse>,
    @field:SerializedName("page")
    var page: Int,
    @field:SerializedName("total_pages")
    var totalPages: Int,
    @field:SerializedName("total_results")
    var totalResults: Int,
)

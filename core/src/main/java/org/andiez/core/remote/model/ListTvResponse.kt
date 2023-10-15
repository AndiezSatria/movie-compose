package org.andiez.core.remote.model

import com.google.gson.annotations.SerializedName

data class ListTvResponse(
    @field:SerializedName("results")
    var results: List<TvShowResponse>,
    @field:SerializedName("page")
    var page: Int,
    @field:SerializedName("total_pages")
    var totalPages: Int,
    @field:SerializedName("total_results")
    var totalResults: Int,
)
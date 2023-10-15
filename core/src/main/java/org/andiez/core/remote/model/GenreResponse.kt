package org.andiez.core.remote.model

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @field:SerializedName("id")
    var id: Int = 0,
    @field:SerializedName("name")
    var name: String = ""
)
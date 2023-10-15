package org.andiez.core.remote.model

import com.google.gson.annotations.SerializedName

data class CastResponse(
    @field:SerializedName("id")
    var id: Int = 0,
    @field:SerializedName("character")
    var character: String = "",
    @field:SerializedName("name")
    var name: String = "",
    @field:SerializedName("original_name")
    var originalName: String = "",
    @field:SerializedName("profile_path")
    var img: String? = ""
)
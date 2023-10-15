package org.andiez.core.remote.model

import com.google.gson.annotations.SerializedName

data class SpokenLanguages(
    @field:SerializedName("name")
    var name: String = ""
)

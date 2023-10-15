package org.andiez.core.remote.model

import com.google.gson.annotations.SerializedName
import org.andiez.core.remote.model.CastResponse

data class ListCastResponse(
    @field:SerializedName("cast")
    var casts: List<CastResponse> = emptyList()
)

package com.footballproject.data.remote.response.teams


import com.google.gson.annotations.SerializedName

data class Filters(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("permission")
    val permission: String?,
)

package com.footballproject.data.remote.response.team


import com.google.gson.annotations.SerializedName

data class Area(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("flag")
    val flag: String?,
)

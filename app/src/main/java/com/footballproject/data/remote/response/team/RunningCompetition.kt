package com.footballproject.data.remote.response.team


import com.google.gson.annotations.SerializedName

data class RunningCompetition(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("emblem")
    val emblem: String?,
)

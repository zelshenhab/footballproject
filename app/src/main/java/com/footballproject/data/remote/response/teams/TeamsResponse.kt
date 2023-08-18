package com.footballproject.data.remote.response.teams


import com.google.gson.annotations.SerializedName

data class TeamsResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("filters")
    val filters: Filters?,
    @SerializedName("teams")
    val teams: List<Team>,
)

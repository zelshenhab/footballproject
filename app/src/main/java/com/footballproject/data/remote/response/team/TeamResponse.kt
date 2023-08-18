package com.footballproject.data.remote.response.team


import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("area")
    val area: Area?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String,
    @SerializedName("shortName")
    val shortName: String?,
    @SerializedName("tla")
    val tla: String?,
    @SerializedName("crest")
    val crest: String?,
    @SerializedName("address")
    val address: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("founded")
    val founded: Int?,
    @SerializedName("clubColors")
    val clubColors: String?,
    @SerializedName("venue")
    val venue: String?,
    @SerializedName("runningCompetitions")
    val runningCompetitions: List<RunningCompetition>?,
    @SerializedName("coach")
    val coach: Coach?,
    @SerializedName("squad")
    val squad: List<Squad>?,
    @SerializedName("staff")
    val staff: List<Staff>?,
    @SerializedName("lastUpdated")
    val lastUpdated: String?,
)

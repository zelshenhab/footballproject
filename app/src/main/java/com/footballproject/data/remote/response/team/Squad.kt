package com.footballproject.data.remote.response.team


import com.google.gson.annotations.SerializedName

data class Squad(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("position")
    val position: String?,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String?,
    @SerializedName("nationality")
    val nationality: String?,
)

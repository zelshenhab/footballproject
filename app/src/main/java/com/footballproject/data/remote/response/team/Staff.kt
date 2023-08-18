package com.footballproject.data.remote.response.team


import com.google.gson.annotations.SerializedName

data class Staff(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String?,
    @SerializedName("nationality")
    val nationality: String?,
    @SerializedName("contract")
    val contract: Contract?,
)

package com.footballproject.data.remote

import com.footballproject.data.remote.response.team.TeamResponse
import com.footballproject.data.remote.response.teams.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballApi {
    //todo
    @GET("teams?limit=100&offset=100")
    suspend fun getTeams(
    ): TeamsResponse

    @GET("teams/{id}")
    suspend fun getTeamById(
        @Path("id") id: Int,
    ): TeamResponse

}

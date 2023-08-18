package com.footballproject.domain.repository

import com.footballproject.data.remote.response.team.TeamResponse
import com.footballproject.data.remote.response.teams.TeamsResponse

interface Repository {

    suspend fun getTeams(): TeamsResponse

    suspend fun getTeamById(
        id: Int,
    ): TeamResponse

}

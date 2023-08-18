package com.footballproject.data.repository

import com.footballproject.data.remote.FootballApi
import com.footballproject.data.remote.response.team.TeamResponse
import com.footballproject.data.remote.response.teams.TeamsResponse
import com.footballproject.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: FootballApi,
) : Repository {

    override suspend fun getTeams(): TeamsResponse = api.getTeams()

    override suspend fun getTeamById(id: Int): TeamResponse = api.getTeamById(id)

}

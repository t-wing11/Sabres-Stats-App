package com.example.sabresapp.network

import ApolloInstance
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sabresapp.PlayerListQuery
import com.example.sabresapp.RosterListQuery
import com.example.sabresapp.data.Player
import com.example.sabresapp.data.PlayerStats
import com.example.sabresapp.data.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository {

    suspend fun getRoster(season: String, teamData: MutableLiveData<Team?>) {
        try {
            // Execute the GraphQL query in a background thread
            val response = withContext(Dispatchers.IO) {
                ApolloInstance.apolloClient.query(RosterListQuery(season)).execute()
            }

            // Safely handle potential null data
            val team = response.data?.teamRoster?.let { roster ->
                Team(
                    name = "Buffalo Sabres",
                    season = season,
                    players = roster.map { teamRoster ->
                        teamRoster.let {
                                Player(
                                    id = it.id,
                                    firstName = it.firstName,
                                    lastName = it.lastName,
                                    positionCode = it.positionCode,
                                    headshot = it.headshot,
                                    sweaterNumber = it.sweaterNumber,
                                    shoots = it.shootsCatches,
                                )
                        }
                    }
                )
            }

            teamData.postValue(team)
        } catch (e: Exception) {
            // Handle errors (e.g., log or pass a meaningful error message)
            teamData.postValue(null)
        }
    }
    suspend fun getPlayerInfo(playerId: String, playerData: MutableLiveData<Player?>) {
        try {
            // Execute the GraphQL query in a background thread
            val response = withContext(Dispatchers.IO) {
                ApolloInstance.apolloClient.query(PlayerListQuery(playerId)).execute()
            }
            Log.d("tag", "response ${response.errors}")


            // Safely handle potential null data
            val player = response.data?.playerInfo?.let { playerInfo ->
                Player(
                    id = playerInfo.playerId,
                    firstName = playerInfo.firstName,
                    lastName = playerInfo.lastName,
                    positionCode = playerInfo.position,
                    headshot = playerInfo.headshot,
                    sweaterNumber = playerInfo.sweaterNumber,
                    shoots = playerInfo.shootsCatches,
                    height = playerInfo.heightInInches,
                    weight = playerInfo.weightInPounds,
                    birthDate = playerInfo.birthDate,
                    birthCity = playerInfo.birthCity,
                    birthStateProvince = playerInfo.birthStateProvince,
                    birthCountry = playerInfo.birthCountry,
                    seasonTotals = playerInfo.seasonTotals.map { seasonTotal ->
                        PlayerStats(
                            season = seasonTotal.season,
                            gamesPlayed = seasonTotal.gamesPlayed,
                            goals = seasonTotal.goals,
                            assists = seasonTotal.assists,
                            points = seasonTotal.points,
                            team = seasonTotal.teamName
                        )
                    }
                )
            }
            playerData.postValue(player)
        } catch (e: Exception) {
            // Handle errors (e.g., log or pass a meaningful error message)
            playerData.postValue(null)
        }
    }
}
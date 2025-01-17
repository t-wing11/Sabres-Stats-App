package com.example.sabresapp.network

import ApolloInstance
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloClient
import com.example.sabresapp.RosterListQuery
import com.example.sabresapp.data.Player
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
                                    height = it.heightInInches,
                                    weight = it.weightInPounds,
                                    birthDate = it.birthDate,
                                    birthCity = it.birthCity,
                                    birthStateProvince = it.birthStateProvince,
                                    birthCountry = it.birthCountry
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
}
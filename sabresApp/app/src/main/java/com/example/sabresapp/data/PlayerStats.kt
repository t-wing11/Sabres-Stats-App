package com.example.sabresapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerStats(
    val season: String,
    val team: String,
    val gamesPlayed: Int,
    val goals: Int,
    val assists: Int,
    val points: Int,
) : Parcelable

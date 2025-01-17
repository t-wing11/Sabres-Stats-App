package com.example.sabresapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val id: String,
    val firstName: String,
    val lastName: String,
    val positionCode: String,
    val headshot: String,
    val sweaterNumber: Int?,
    val shoots: String,
    val height: String = "",
    val weight: Int? = null,
    val birthDate: String? = null,
    val birthCity: String? = null,
    val birthStateProvince: String? = null,
    val birthCountry: String? = null,
    val seasonTotals: List<PlayerStats>? = null
) : Parcelable

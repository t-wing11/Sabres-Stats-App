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
    val height: Int,
    val weight: Int,
    val birthDate: String,
    val birthCity: String,
    val birthStateProvince: String?,
    val birthCountry: String,
) : Parcelable

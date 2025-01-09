package com.cursosant.forecastweatherbase.entities.forecast

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h") val oneHour: Double
)

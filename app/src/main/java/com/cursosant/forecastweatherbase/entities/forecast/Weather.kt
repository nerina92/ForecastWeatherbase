package com.cursosant.forecastweatherbase.entities.forecast

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

package com.cursosant.forecastweatherbase.entities.forecast

data class WeatherForecastEntity2(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherData>,
    val city: City
)

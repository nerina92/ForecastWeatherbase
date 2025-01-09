package com.cursosant.forecastweatherbase.mainModule.model

import com.cursosant.forecastweatherbase.common.dataAccess.WeatherForecastService
import com.cursosant.forecastweatherbase.entities.WeatherForecastEntity
import com.cursosant.forecastweatherbase.entities.forecast.ApiResponse
import com.cursosant.forecastweatherbase.entities.forecast.WeatherForecastEntity2

/****
 * Project: Forecast Weather
 * From: com.cursosant.forecastweather.mainModule.model
 * Created by Alain Nicolás Tello on 15/12/21 at 20:40
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
class MainRepository(private val service: WeatherForecastService) {
    suspend fun getWeatherAndForecast(lat: Double, lon: Double, appId: String, units: String,
                                      lang: String) : WeatherForecastEntity {
        return service.getWeatherForecastByCoordinates(lat, lon, appId, units, lang)
    }
    suspend fun getWeatherForecast(lat: Double, lon: Double, appId: String) : WeatherForecastEntity2 {
        return service.getWeatherForecastByCoordinates(lat, lon, appId)//, units, lang)
    }
    suspend fun getWeather(lat: Double, lon: Double, appId: String, units: String,
                           lang: String) : ApiResponse {
        return service.getWeather(lat, lon, appId, units, lang)
    }
}
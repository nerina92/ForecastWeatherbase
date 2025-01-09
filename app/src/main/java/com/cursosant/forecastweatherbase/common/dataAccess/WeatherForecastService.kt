package com.cursosant.forecastweatherbase.common.dataAccess

import com.cursosant.forecastweatherbase.entities.WeatherForecastEntity
import com.cursosant.forecastweatherbase.entities.forecast.ApiResponse
import com.cursosant.forecastweatherbase.entities.forecast.WeatherForecastEntity2
import retrofit2.http.GET
import retrofit2.http.Query

/****
 * Project: Forecast Weather
 * From: com.cursosant.forecastweather.common.dataAccess
 * Created by Alain Nicolás Tello on 15/12/21 at 20:35
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
interface WeatherForecastService {
    @GET("data/2.5/onecall")
    suspend fun getWeatherForecastByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("units") units: String,
        @Query("lang") lang: String): WeatherForecastEntity

    @GET("data/2.5/forecast/hourly")
    suspend fun getWeatherForecastByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String) : WeatherForecastEntity2

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("units") units: String,
        @Query("lang") lang: String) : ApiResponse
}
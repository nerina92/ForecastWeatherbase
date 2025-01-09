package com.cursosant.forecastweatherbase.entities

/****
 * Project: Forecast Weather
 * From: com.cursosant.forecastweather.entities
 * Created by Alain Nicolás Tello on 15/12/21 at 20:35
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
data class Forecast(val dt: Long,
                    val temp: Double,
                    val humidity: Int,
                    val weather: List<Weather>,
                    val pop: Double)

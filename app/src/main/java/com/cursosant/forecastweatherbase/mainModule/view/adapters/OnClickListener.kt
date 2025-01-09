package com.cursosant.forecastweatherbase.mainModule.view.adapters

import com.cursosant.forecastweatherbase.entities.Forecast
import com.cursosant.forecastweatherbase.entities.forecast.WeatherData

/****
 * Project: Forecast Weather
 * From: com.cursosant.forecastweather.mainModule.view.adapters
 * Created by Alain Nicolás Tello on 15/12/21 at 20:42
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
interface OnClickListener {
    fun onClick(forecast: WeatherData)
}
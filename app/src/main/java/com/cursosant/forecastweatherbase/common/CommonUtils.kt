package com.cursosant.forecastweatherbase.common

import com.cursosant.forecastweatherbase.entities.forecast.Weather
import java.text.SimpleDateFormat
import java.util.*

/****
 * Project: Forecast Weather
 * From: com.cursosant.forecastweather.common
 * Created by Alain Nicolás Tello on 15/12/21 at 20:34
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
object CommonUtils {
    fun getHour(epoch: Long): String = getFormatedTime(epoch, "HH:mm")

    fun getFullDate(epoch: Long): String = getFormatedTime(epoch, "dd/MM/yy HH:mm")

    private fun getFormatedTime(epoch: Long, pattern: String): String{
        return SimpleDateFormat(pattern, Locale.getDefault()).format(epoch * 1_000)
    }

    fun getWeatherMain(weather: List<Weather>): String {
        return if (!weather.isEmpty()) weather[0].main else "-"
    }

    fun getWeatherDescription(weather: List<Weather>): String {
        return if (!weather.isEmpty()) weather[0].description else "-"
    }
}
package com.cursosant.forecastweatherbase.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosant.forecastweatherbase.common.dataAccess.WeatherForecastService
import com.cursosant.forecastweatherbase.entities.WeatherForecastEntity
import com.cursosant.forecastweatherbase.entities.forecast.ApiResponse
import com.cursosant.forecastweatherbase.entities.forecast.WeatherForecastEntity2
import com.cursosant.forecastweatherbase.mainModule.model.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/****
 * Project: Forecast Weather
 * From: com.cursosant.forecastweather.mainModule.viewModel
 * Created by Alain Nicolás Tello on 15/12/21 at 20:41
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
class MainViewModel : ViewModel() {
    private val result = MutableLiveData<ApiResponse>()

    fun getResult(): LiveData<ApiResponse> = result

    private val repository: MainRepository by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherForecastService::class.java)
        MainRepository(service)
    }

    suspend fun getWeatherAndForecast(lat: Double, lon: Double, appId: String, units: String, lang: String){
        viewModelScope.launch {
            //val resultServer = repository.getWeatherAndForecast(lat, lon, appId, units, lang)
            val resultServer = repository.getWeather(lat, lon, appId, units, lang)
            result.value = resultServer
        }
    }
}
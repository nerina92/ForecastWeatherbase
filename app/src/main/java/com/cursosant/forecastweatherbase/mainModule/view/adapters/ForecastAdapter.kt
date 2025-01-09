package com.cursosant.forecastweatherbase.mainModule.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cursosant.forecastweatherbase.R
import com.cursosant.forecastweatherbase.common.CommonUtils
import com.cursosant.forecastweatherbase.databinding.ItemForecastWeatherBinding
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
class ForecastAdapter(private val listener: OnClickListener) :
    ListAdapter<WeatherData, RecyclerView.ViewHolder>(ForecastDiffCallback()) {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_forecast_weather,
            parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val forecast = getItem(position)

        with(holder as ViewHolder){
            setListener(forecast)

            binding.tvTemp.text = context.getString(R.string.weather_temp, forecast.main.temp)
            binding.tvDt.text = CommonUtils.getHour(forecast.dt)
            binding.tvHumidity.text = context.getString(R.string.weather_humidity, forecast.main.humidity)
            binding.tvPop.text = context.getString(R.string.weather_pop, forecast.pop)
            binding.tvMain.text = CommonUtils.getWeatherMain(forecast.weather)
            binding.tvDescription.text = CommonUtils.getWeatherDescription(forecast.weather)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemForecastWeatherBinding.bind(view)

        fun setListener(forecast: WeatherData){
            binding.root.setOnClickListener { listener.onClick(forecast) }
        }
    }

    class ForecastDiffCallback: DiffUtil.ItemCallback<WeatherData>() {
        override fun areItemsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean = oldItem.dt == newItem.dt

        override fun areContentsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean = oldItem == newItem
    }
}
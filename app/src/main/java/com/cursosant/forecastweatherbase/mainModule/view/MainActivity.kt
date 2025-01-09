package com.cursosant.forecastweatherbase.mainModule.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursosant.forecastweatherbase.R
import com.cursosant.forecastweatherbase.common.CommonUtils
import com.cursosant.forecastweatherbase.databinding.ActivityMainBinding
import com.cursosant.forecastweatherbase.entities.forecast.ApiResponse
import com.cursosant.forecastweatherbase.entities.forecast.WeatherData
import com.cursosant.forecastweatherbase.mainModule.view.adapters.ForecastAdapter
import com.cursosant.forecastweatherbase.mainModule.view.adapters.OnClickListener
import com.cursosant.forecastweatherbase.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

/****
 * Project: Forecast Weatherbase
 * From: com.cursosant.forecastweatherbase.mainModule.view
 * Created by Alain Nicolás Tello on 22/05/23 at 15:09
 * All rights reserved 2023.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
class MainActivity : AppCompatActivity() , OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ForecastAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupAdapter() {
        adapter = ForecastAdapter(this)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    override fun onStart() {
        super.onStart()
        setupData()
    }

    private fun setupData() {
        lifecycleScope.launch {
            mainViewModel.getWeatherAndForecast(-34.127372, -63.390917,
                "7e036470c5dbb57040957bdb616a89b1", "metric", "es")
        }
    }
    private fun setupViewModel(){
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getResult().observe(this){ result ->
            setupUI(result)
        }
    }

    private fun setupUI(data: ApiResponse) {
        with(binding) {
            tvTimeZone.text = data.name
            current.tvTemp.text = getString(R.string.weather_temp, data.main.temp)
            current.tvDt.text = CommonUtils.getFullDate(data.dt)
            current.tvHumidity.text =getString(R.string.weather_humidity, data.main.humidity)
            current.tvMain.text = CommonUtils.getWeatherMain(data.weather)
            current.tvDescription.text = CommonUtils.getWeatherDescription(data.weather)
        }
        //adapter.submitList(data.list)
    }

    //https://openweathermap.org/api/one-call-api#current
    /*private suspend fun getHistoricalWeather(): WeatherForecastEntity = withContext(Dispatchers.IO) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherForecastService::class.java)
        service.getWeatherForecastByCoordinates(-34.127372, -63.390917, "d11b343eaad9430ea856e3f8127d344c",
            "metric", "es")
    }*/
    override fun onClick(forecast: WeatherData) {
        Snackbar.make(binding.root, CommonUtils.getFullDate(forecast.dt), Snackbar.LENGTH_LONG).show()
    }
}
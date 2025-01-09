package com.cursosant.forecastweatherbase.mainModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cursosandroidant.historicalweatherref.getOrAwaitValue
import com.cursosant.forecastweatherbase.common.dataAccess.WeatherForecastService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModelTest{
    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    //val mainCorrutineRule = MainCorrutineRule()
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var service: WeatherForecastService

    companion object{
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Before
    fun setup(){
        mainViewModel = MainViewModel()
        service = retrofit.create(WeatherForecastService::class.java)
    }

    @Test
    fun checkWeatherIsNotNullTest() = runTest {
        val result = service.getWeather(
            -34.127372, -63.390917,
            "7e036470c5dbb57040957bdb616a89b1", "metric", "es"
        )
        assertThat(result, `is`(notNullValue()))
    }
    @Test
    fun checkWeatherTimezoneReturnLaboulayeCityTest() = runTest{
        val result = service.getWeather(
            -34.127372, -63.390917,
            "7e036470c5dbb57040957bdb616a89b1", "metric", "es"
        )
        assertThat(result.name, `is`("Laboulaye"))
    }

    @Test
    fun checkErrorResponseWithOnlyCoordinateTest() = runTest {
        try{
            service.getWeather(
                -34.127372, -63.390917,
                "", "", ""
            )
        }catch (e: Exception) {
            assertThat(e.message, `is`("HTTP 401 Unauthorized"))
        }
    }

   /* @Test
    fun checkHourlySizeTest(){
        runBlocking {
            /* de esta forma no anda porque lo intenta comparar antes de que se actualice el live data***
            val observer = Observer<ApiResponse>{}
            try {
                mainViewModel.getResult().observeForever(observer)
                mainViewModel.getWeatherAndForecast(
                    -34.127372, -63.390917,
                    "7e036470c5dbb57040957bdb616a89b1", "metric", "es"
                )
                val result = mainViewModel.getResult().value
                assertThat(result?.sys?.country ?: "", `is`("AR"))
            }finally {
                mainViewModel.getResult().removeObserver (observer)
            }
            // Formacorrecta debajo: ******/
            mainViewModel.getWeatherAndForecast(
                -34.127372, -63.390917,
                "7e036470c5dbb57040957bdb616a89b1", "metric", "es"
            )
            val result = mainViewModel.getResult().getOrAwaitValue()
            assertThat(result.sys.country , `is`("AR"))
        }
    }*/

    //Nueva forma actualizada de test con corrutinas
    @Test
    fun checkHourlySizeTest() = runTest {
        mainViewModel.getWeatherAndForecast(
            -34.127372, -63.390917,
            "7e036470c5dbb57040957bdb616a89b1", "metric", "es"
        )
        val result = mainViewModel.getResult().getOrAwaitValue()
        assertThat(result.sys.country , `is`("AR"))
    }

}

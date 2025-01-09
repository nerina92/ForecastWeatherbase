package com.cursosant.forecastweatherbase.mainModule.viewModel.common.dataAccess

import com.cursosant.forecastweatherbase.entities.forecast.ApiResponse
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class ResponseServerTest {
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun readJSONFileSuccess() {
        val reader = JSONFileLoader().loadJSONString("api_response_weather_succes.json")
        assertThat(reader, `is`(notNullValue()))
        assertThat(reader, containsString("coord"))
    }

    @Test
    fun getWeatherCheckTimezoneExist(){
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(JSONFileLoader().loadJSONString("api_response_weather_succes.json")?:"{errorCode:34}")
        mockWebServer.enqueue(response)
        assertThat(response.getBody()?.readUtf8(), containsString("\"timezone\""))
    }

    @Test
    fun getWeatherCheckFailResponse(){
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(JSONFileLoader().loadJSONString("api_response_weather_fail.json")?:"{errorCode:34}")
        mockWebServer.enqueue(response)
        assertThat(response.getBody()?.readUtf8(), containsString("\"cod\": 401,"))
    }

    //con el uso de badticks en el nombre de los metodos se pueden usar espacios y ser mas específico
    @Test
    fun `get weather check contains main`(){
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(JSONFileLoader().loadJSONString("api_response_weather_succes.json")?:"{errorCode:34}")
        mockWebServer.enqueue(response)
        assertThat(response.getBody()?.readUtf8(), containsString("\"main\":"))
        val json = Gson().fromJson(response.getBody()?.readUtf8() ?: "", ApiResponse::class.java)
        assertThat(json.main , notNullValue())
    }
}
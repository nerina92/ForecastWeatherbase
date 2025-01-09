package com.cursosant.forecastweatherbase.mainModule.viewModel.common.dataAccess

import com.cursosant.forecastweatherbase.entities.forecast.ApiResponse
import com.google.gson.Gson
import java.io.InputStreamReader

class JSONFileLoader {
    private var jsonStr: String? = null

    fun loadJSONString(file:String):String?{
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
        jsonStr=loader.readText()
        loader.close()
        return jsonStr
    }

    fun loadApiResponseEntity(file: String) : ApiResponse{
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
        jsonStr=loader.readText()
        loader.close()
        return Gson().fromJson(jsonStr, ApiResponse::class.java)
    }

}
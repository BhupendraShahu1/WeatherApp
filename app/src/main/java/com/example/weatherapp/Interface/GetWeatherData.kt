package com.example.weatherapp.Interface

import com.example.weatherapp.Model.WeatherModelClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetWeatherData {
    @GET("v1/current.json?key=1edb6e641ced4f1c879115426230112")
    open fun geData(
        @Query("q") city: String?,
        @Query("aqi") aqi: String?
    ): Call<WeatherModelClass?>?
}
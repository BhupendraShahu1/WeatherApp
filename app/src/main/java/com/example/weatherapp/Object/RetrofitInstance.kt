package com.example.weatherapp.Object

import com.example.weatherapp.Interface.GetWeatherData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
   private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val weatherData by lazy {
        retrofit.create(GetWeatherData::class.java)
    }
}
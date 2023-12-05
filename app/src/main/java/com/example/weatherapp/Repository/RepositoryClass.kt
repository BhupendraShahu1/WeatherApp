package com.example.weatherapp.Repository

import com.example.weatherapp.Interface.GetWeatherData
import com.example.weatherapp.Model.WeatherModelClass
import retrofit2.Call

class RepositoryClass(private val weatherData: GetWeatherData){
    fun getWeatherData(city: String?, aqi: String?): Call<WeatherModelClass?>?{
       return weatherData.geData(city,aqi)

    }

}
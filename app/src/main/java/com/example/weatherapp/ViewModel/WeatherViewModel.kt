package com.example.weatherapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.Model.WeatherModelClass
import com.example.weatherapp.Object.RetrofitInstance
import com.example.weatherapp.Repository.RepositoryClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(application: Application) :
    AndroidViewModel(application) {
    private var weatherData = MutableLiveData<WeatherModelClass?>()

    private var repo: RepositoryClass = RepositoryClass(RetrofitInstance.weatherData)

    fun getPopularMovies(search: String) {
        val country = "India"
        val result = "$search, $country"
        repo.getWeatherData(result, "yes")
            ?.enqueue(object : Callback<WeatherModelClass?> {
                override fun onResponse(
                    call: Call<WeatherModelClass?>,
                    response: Response<WeatherModelClass?>
                ) {
                    weatherData.value = response.body()
                }
                override fun onFailure(call: Call<WeatherModelClass?>, t: Throwable) {
                }
            })
    }

    fun observeMovieLiveData(): MutableLiveData<WeatherModelClass?> {
        return weatherData
//        weatherData.value= response.body()
    }
}

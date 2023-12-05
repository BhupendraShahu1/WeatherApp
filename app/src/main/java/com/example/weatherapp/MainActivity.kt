package com.example.weatherapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.ViewModel.WeatherViewModel
import com.example.weatherapp.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var viewModel: WeatherViewModel
    private var searchValue: String = "Noida"


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        activityMainBinding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchValue = query
                    viewModel.getPopularMovies(searchValue)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        viewModel.getPopularMovies(searchValue)
        viewModel.observeMovieLiveData().observe(this, Observer {
            if (it != null) {
                val x = LocalDateTime.now()
                val setHour = x.hour
                if (setHour < 12) {
                    activityMainBinding.currentDay.text = "Good Morning"
                } else if (setHour in 12..17) {
                    activityMainBinding.currentDay.text = "Good AfterNoon"
                } else if (setHour in 17..20) {
                    activityMainBinding.currentDay.text = "Good Evening"
                } else if (setHour in 20..24) {
                    activityMainBinding.currentDay.text = "Good Night"
                }
                activityMainBinding.temp.text = it.current.temp_c.toString()
                activityMainBinding.conditionText.text = it.current.condition.text
                activityMainBinding.location.text = it.location.name
                activityMainBinding.humidity.text = it.current.humidity.toString() + " %"
                activityMainBinding.windSpeed.text = it.current.wind_kph.toString() + " Km/h"
                activityMainBinding.lat.text = "lat: " + it.location.lat.toString()
                activityMainBinding.lon.text = "lon: " + it.location.lon.toString()
                //

                val toString = it.current.condition.text
                if (toString == "Mist" || toString == "Cloud" || toString == "Overcast" || toString == "Partly cloudy") {
                    activityMainBinding.constraint.setBackgroundResource(R.drawable.snow)
//                    window.statusBarColor = getColor(R.color.light_black)
                    activityMainBinding.loti.setAnimation(R.raw.winter_lotti)
                    activityMainBinding.loti.playAnimation()

                } else {
                    activityMainBinding.constraint.setBackgroundResource(R.drawable.brownbackground)
//                    window.statusBarColor = getColor(R.color.yellow)
                    activityMainBinding.loti.setAnimation(R.raw.sunny)
                    activityMainBinding.loti.playAnimation()
                }

                val todayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
                val daySet = DateTimeFormatter.ofPattern("EEEE")
                val todayFormatted = x.format(todayFormatter)
                val setDay = x.format(daySet)
                if (it.current.temp_c < 18) {
                    activityMainBinding.lottieBackgroundImage.visibility =
                        View.VISIBLE//lottie background when cold weather
                    activityMainBinding.loti.setAnimation(R.raw.winter_lotti)// image is cold when cold
//                    window.statusBarColor = getColor(R.color.brown)// use for change the status bar color
                    activityMainBinding.loti.playAnimation()

                } else {
                    activityMainBinding.lottieBackgroundImage.visibility =
                        View.GONE
                    activityMainBinding.loti.setAnimation(R.raw.sunny)
                    activityMainBinding.loti.playAnimation()
                }
                if (setHour > 18) {
                        activityMainBinding.loti.setAnimation(R.raw.night_anim)
                        activityMainBinding.lottieBackgroundImage.setAnimation(R.raw.night_background)
                        activityMainBinding.loti.playAnimation()
                    }
                activityMainBinding.year.text = todayFormatted
                activityMainBinding.day.text = setDay

//                activityMainBinding.conditionR.text = it.current.condition.toString()
//                activityMainBinding.sunRise.text=it.current.
            }
        })
    }

}

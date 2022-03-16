package com.example.retrofitforecaster

import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MyViewModel : ViewModel() {
    private var weatherList: MutableList<ListItem>? = null

    fun getWeatherData(): List<ListItem> {
        if (weatherList == null) {
            weatherList = mutableListOf()
            loadWeatherData()
        }
        return weatherList as MutableList<ListItem>
    }


    private fun loadWeatherData(){
        val mService = Common.retrofitService

        mService.getWeatherList().enqueue(object : Callback<DataWeather> {
            override fun onResponse(call: Call<DataWeather>, response: Response<DataWeather>) {
                val dataWeather = response.body() as DataWeather
                weatherList = dataWeather.list as MutableList<ListItem>

            }

            override fun onFailure(call: Call<DataWeather>, t: Throwable) {
            }
        })

    }
}
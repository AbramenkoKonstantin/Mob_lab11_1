package com.example.retrofitforecaster

import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MyViewModel : ViewModel() {
    private val weatherData: MutableList<List<ListItem>> by lazy {
        mutableListOf<List<ListItem>>().also {
            loadWeatherData()
        }
    }

    public fun getWeather(): List<ListItem> {
        return weatherData[weatherData.lastIndex]
    }



    private fun loadWeatherData() {
        val mService = Common.retrofitService
        Timber.plant(Timber.DebugTree())
        Timber.d("Привет")
        mService.getWeatherList().enqueue(object : Callback<DataWeather> {
            override fun onResponse(call: Call<DataWeather>, response: Response<DataWeather>) {
                val dataWeather = response.body() as DataWeather
                weatherData.add(dataWeather.list)
            }

            override fun onFailure(call: Call<DataWeather>, t: Throwable) {
            }
        })

    }
}
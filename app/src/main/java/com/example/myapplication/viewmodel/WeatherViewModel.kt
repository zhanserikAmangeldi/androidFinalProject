package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.network.ApiClient
import com.example.myapplication.network.ApiService
import com.example.myapplication.repository.WeatherRepository

class WeatherViewModel(val repository: WeatherRepository): ViewModel() {

    constructor():this(WeatherRepository(ApiClient().getClient().create(ApiService::class.java)))

    fun loadCurrentWeather(lat: Double, lon: Double, unit: String)=
        repository.getCurrentWeather(lat, lon, unit)


}
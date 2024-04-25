package com.example.myapplication.repository

import com.example.myapplication.network.ApiService

class WeatherRepository(val api: ApiService) {
    fun getCurrentWeather(lat: Double, lon: Double, unit: String)=
        api.getCurrentWeather(lat, lon, unit, "3c379ea575d50193e00159dbd8294ab7")
}
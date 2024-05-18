package com.example.last.model.repository

import android.util.Log
import com.example.last.model.entity.WeatherResponse
import com.example.last.model.network.WeatherApiInterface

class WeatherResponseRepository(private val weatherApiService: WeatherApiInterface) {

    suspend fun getWeatherByGPS(latitude: String, longitude: String, units: String): WeatherResponse? {
        try{
            val response = weatherApiService.getWeatherByGPS(latitude, longitude, units)
            if(response.isSuccessful){
                return response.body()
            } else{
                Log.e("WeatherResponseRepository", "Failed to fetch weather response: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception){
            Log.e("WeatherResponseRepository", "Exception when fetching weather response", e)
        }
        return null
    }

}
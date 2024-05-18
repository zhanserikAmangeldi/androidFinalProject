package com.example.last.model.repository

import android.util.Log
import com.example.last.model.entity.ForecastResponse
import com.example.last.model.network.WeatherApiInterface

class ForecastResponseRepository(private val weatherApiService: WeatherApiInterface) {

    suspend fun getForecastByGPS(latitude: String, longitude: String, units: String) : ForecastResponse?{
        try{
            val response = weatherApiService.getForecastByGPS(latitude, longitude, units)
            if(response.isSuccessful){
                return response.body()
            } else{
                Log.e("ForecastResponseRepository", "Failed to fetch forecast response: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception){
            Log.e("ForecastResponseRepository", "Exception when fetching forecast response", e)
        }
        return null

    }

}
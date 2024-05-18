package com.example.last.model.repository

import android.util.Log
import com.example.last.model.entity.CityDailyResponse
import com.example.last.model.network.WeatherApiInterface

class CityDailyResponseRepository(private val weatherApiService: WeatherApiInterface) {

    suspend fun getCityDailyWeatherByGPS(latitude: String, longitude: String, cnt: String, units: String): CityDailyResponse? {
        try{
            val response = weatherApiService.getCityDailyWeatherByGPS(latitude, longitude, cnt, units)
            if(response.isSuccessful){
                return response.body()
            } else{
                Log.e("CityDailyResponseRepository", "Failed to fetch city daily response: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception){
            Log.e("CityDailyResponseRepository", "Exception when fetching city daily response", e)
        }
        return null
    }

}
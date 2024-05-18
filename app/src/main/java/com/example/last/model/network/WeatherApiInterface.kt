package com.example.last.model.network

import com.example.last.model.entity.CityDailyResponse
import com.example.last.model.entity.ForecastResponse
import com.example.last.model.entity.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {
    @GET("weather?")
    suspend fun getWeatherByGPS
                (@Query("lat") latitude: String,
                 @Query("lon") longitude: String,
                 @Query("units") units: String
    ): Response<WeatherResponse>

    @GET("forecast?")
    suspend fun getForecastByGPS(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("units") units: String
    ): Response<ForecastResponse>

    @GET("find?")
    suspend fun getCityDailyWeatherByGPS(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("cnt") cnt: String,
        @Query("units") units: String
    ): Response<CityDailyResponse>
}
package com.example.last.model.network

import com.example.last.model.entity.CityDailyResponse
import com.example.last.model.entity.ForecastResponse
import com.example.last.model.entity.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather?")
    fun getWeatherByGPS
                (@Query("lat") latitude: String,
                 @Query("lon") longitude: String,
                 @Query("units") units: String
    ): Single<WeatherResponse>

    @GET("forecast?")
    fun getForecastByGPS(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("units") units: String
    ): Single<ForecastResponse>

    @GET("find?")
    fun getCityDailyWeatherByGPS(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("cnt") cnt: String,
        @Query("units") units: String
    ): Single<CityDailyResponse>
}
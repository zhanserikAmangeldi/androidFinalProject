package com.example.last.model.network

import com.example.last.model.entity.CityResponse
import com.example.last.util.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoApiInterface {
    @GET("direct?")
    suspend fun getCoordinatesByCity(
        @Query("q") cityName: String,
        @Query("appid") key: String = Constant.API_KEY,
        @Query("limit") limit: Int = 5
        ): Response<List<CityResponse>>
}
package com.example.last.model.repository

import android.util.Log
import com.example.last.model.entity.CityResponse
import com.example.last.model.network.GeoApiInterface

class CityResponseRepository(private val geoApi: GeoApiInterface) {

    suspend fun getCoordinatesByCity(cityName: String): List<CityResponse>{
        try{
            val response = geoApi.getCoordinatesByCity(cityName)
            if (response.isSuccessful){
                response.body()?.let {
                    return it
                }
            } else {
                Log.e("CityResponseRepository", "Failed to fetch city response: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception){
            Log.e("CityResponseRepository", "Exception when fetching city response", e)
        }
        return emptyList()

    }

}
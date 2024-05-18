package com.example.last.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class CityDailyResponse(
    @SerializedName("list")  var list: List<CityDailyForecast>? = null,
    @SerializedName("count") var count: Int = 0,
    @SerializedName("cod") var cod: String? = null,
    @SerializedName("message") var message: String? = null
): Parcelable, Serializable

@Parcelize
data class CityDailyForecast(
    @SerializedName("weather") var weather: List<Weather>? = null,
    @SerializedName("clouds") var clouds: Clouds? = null,
    @SerializedName("sys") var sys: CityDailySys? = null,
    @SerializedName("wind") var wind: CityDailyWind? = null,
    @SerializedName("dt") var dt: Int = 0,
    @SerializedName("main")  var main: CityDailyMain? = null,
    @SerializedName("coord") var coord: Coord? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: Int = 0
) : Parcelable, Serializable

@Parcelize
data class CityDailySys(
    @SerializedName("country") var country: String? = null
) : Parcelable, Serializable

@Parcelize
data class CityDailyWind(
    @SerializedName("deg") var deg: Int = 0,
    @SerializedName("speed") var speed: Double = 0.0
) : Parcelable, Serializable

@Parcelize
data class CityDailyMain(
    @SerializedName("humidity") var humidity: Int = 0,
    @SerializedName("pressure") var pressure: Int = 0,
    @SerializedName("temp_max") var tempMax: Double = 0.0,
    @SerializedName("temp_min") var tempMin: Double = 0.0,
    @SerializedName("feels_like") var feelsLike: Double = 0.0,
    @SerializedName("temp") var temp: Double = 0.0
) : Parcelable, Serializable
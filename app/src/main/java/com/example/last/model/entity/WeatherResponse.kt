package com.example.last.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class WeatherResponse(
    @SerializedName("cod") var cod: Int = 0,
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: Int = 0,
    @SerializedName("timezone") var timezone: Int = 0,
    @SerializedName("sys") var sys: WeatherSys? = null,
    @SerializedName("dt") var dt: Int = 0,
    @SerializedName("clouds") var clouds: Clouds? = null,
    @SerializedName("wind") var wind: WeatherWind? = null,
    @SerializedName("visibility") var visibility: Int = 0,
    @SerializedName("main") var main: WeatherMain? = null,
    @SerializedName("base") var base: String? = null,
    @SerializedName("weather") var weather: List<Weather>? = null,
    @SerializedName("coord") var coord: Coord? = null,
) : Parcelable, Serializable

@Parcelize
data class WeatherSys(
    @SerializedName("sunset") var sunset: Int = 0,
    @SerializedName("sunrise") var sunrise: Int = 0,
    @SerializedName("country") var country: String? = null,
    @SerializedName("id") var id: Int = 0,
    @SerializedName("type") var type: Int = 0
) : Parcelable, Serializable

@Parcelize
data class WeatherWind(
    @SerializedName("deg") var deg: Int = 0,
    @SerializedName("speed") var speed: Double = 0.0
) : Parcelable, Serializable

@Parcelize
data class WeatherMain(
    @SerializedName("humidity") var humidity: Int = 0,
    @SerializedName("pressure") var pressure: Int = 0,
    @SerializedName("temp_max") var tempMax: Double = 0.0,
    @SerializedName("temp_min") var tempMin: Double = 0.0,
    @SerializedName("feels_like") var feelsLike: Double = 0.0,
    @SerializedName("temp") var temp: Double = 0.0
) : Parcelable, Serializable

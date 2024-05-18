package com.example.last.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class ForecastResponse(
    @SerializedName("list") var list: List<Forecast>? = null
) : Parcelable, Serializable

@Parcelize
data class Forecast(
    @SerializedName("dt_txt") var dtTxt: String? = null,
    @SerializedName("wind") var wind: ForecastWind? = null,
    @SerializedName("weather") var weather: List<Weather>? = null,
    @SerializedName("main") var main: ForecastMain? = null,
    @SerializedName("dt") var dt: Int = 0
) : Parcelable, Serializable

@Parcelize
data class ForecastWind (
    @SerializedName("speed") var speed: Double = 0.0
) : Parcelable, Serializable

@Parcelize
data class ForecastMain (
    @SerializedName("temp_kf") var tempKf: Double = 0.0,
    @SerializedName("humidity") var humidity: Int = 0,
    @SerializedName("grnd_level") var grndLevel: Int = 0,
    @SerializedName("sea_level") var seaLevel: Int = 0,
    @SerializedName("pressure") var pressure: Int = 0,
    @SerializedName("temp_max") var tempMax: Double = 0.0,
    @SerializedName("temp_min") var tempMin: Double = 0.0,
    @SerializedName("feels_like") var feelsLike: Double = 0.0,
    @SerializedName("temp") var temp: Double = 0.0
) : Parcelable, Serializable
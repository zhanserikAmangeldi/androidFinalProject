package com.example.last.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Coord(
    @SerializedName("lon") var lon: Double = 0.0,
    @SerializedName("lat") var lat: Double = 0.0
) : Parcelable, Serializable

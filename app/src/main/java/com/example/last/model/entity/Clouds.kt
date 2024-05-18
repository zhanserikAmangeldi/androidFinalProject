package com.example.last.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Clouds(
    @SerializedName("all") var all: Int = 0
) : Parcelable, Serializable

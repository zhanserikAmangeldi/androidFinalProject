package com.example.last.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Weather (
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("main") var main: String? = null,
    @SerializedName("id") var id: Int = 0
) : Parcelable, Serializable

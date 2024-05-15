package com.example.last

import android.content.Context
import android.content.SharedPreferences

object PreferenceManager {

    private const val PREFERENCES_FILE = "weather_prefs"
    private const val KEY_CITY_NAME = "saved_city_name"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
    }

    fun saveCityName(context: Context, cityName: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_CITY_NAME, cityName)
        editor.apply()
    }

    fun getCityName(context: Context): String? {
        return getPreferences(context).getString(KEY_CITY_NAME, null)
    }
}

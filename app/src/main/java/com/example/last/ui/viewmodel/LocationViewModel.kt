package com.example.last.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.last.model.entity.WeatherResponse
import com.example.last.model.repository.WeatherResponseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel(private val repository: WeatherResponseRepository, application: Application) : AndroidViewModel(application) {

    val locationData = MutableLiveData<WeatherResponse?>()
    val locationLoading = MutableLiveData<Boolean>()

    fun getWeatherDataWithGPS(latitude: String, longitude: String, units: String) {
        locationLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getWeatherByGPS(latitude, longitude, units)
                locationData.postValue(response)
                locationLoading.postValue(false)
            } catch (e: Exception) {
                Log.e("LocationViewModel", "Exception in fetching weather data", e)
                locationLoading.postValue(false)
            }
        }
    }

}

class LocationViewModelFactory(private val repository: WeatherResponseRepository, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LocationViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
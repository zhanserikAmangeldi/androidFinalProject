package com.example.last.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.last.model.entity.Forecast
import com.example.last.model.repository.ForecastResponseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FiveDaysViewModel(private val repository: ForecastResponseRepository, application: Application) : AndroidViewModel(application) {

    private val _forecastData = MutableLiveData<List<Forecast>>()
    var forecastDataList : LiveData<List<Forecast>> = _forecastData
    val fiveDaysLoading = MutableLiveData<Boolean>()

    fun getForecastFromGps(latitude: String, longitude: String, units: String) {
        fiveDaysLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getForecastByGPS(latitude, longitude, units)
                response?.list?.let {
                    _forecastData.postValue(it)
                } ?: Log.e("FiveDaysViewModel", "Failed to get forecast data.")
                fiveDaysLoading.postValue(false)
            } catch (e: Exception) {
                Log.e("FiveDaysViewModel", "Exception in fetching forecast data", e)
                fiveDaysLoading.postValue(false)
            }
        }
    }

}

class FiveDaysViewModelFactory(private val repository: ForecastResponseRepository, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FiveDaysViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FiveDaysViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
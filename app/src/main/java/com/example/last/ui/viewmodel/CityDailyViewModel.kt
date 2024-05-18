package com.example.last.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.last.model.entity.CityDailyForecast
import com.example.last.model.repository.CityDailyResponseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CityDailyViewModel(private val repository: CityDailyResponseRepository, application: Application) : AndroidViewModel(application) {

    private val _cityDailyData = MutableLiveData<List<CityDailyForecast>>()
    val cityDailyDataList: LiveData<List<CityDailyForecast>> = _cityDailyData
    val cityDailyLoading = MutableLiveData<Boolean>()

    fun getCityDailyWeatherFromGps(latitude: String, longitude: String, cnt: String, units: String) {
        cityDailyLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCityDailyWeatherByGPS(latitude, longitude, cnt, units)
            response?.list?.let {
                _cityDailyData.postValue(it)
            }
            cityDailyLoading.postValue(false)
        }
    }
}

class CityDailyViewModelFactory(private val repository: CityDailyResponseRepository, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityDailyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityDailyViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.last.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.last.model.entity.CityResponse
import com.example.last.model.repository.CityResponseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel(private val repository: CityResponseRepository, application: Application) : AndroidViewModel(application) {

    private val _cityData = MutableLiveData<List<CityResponse>>()
    val cityData: LiveData<List<CityResponse>> = _cityData

    fun getCoordinatesByCity(cityName: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCoordinatesByCity(cityName)
            _cityData.postValue(response)
        }
    }

}

class CityViewModelFactory(private val repository: CityResponseRepository, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
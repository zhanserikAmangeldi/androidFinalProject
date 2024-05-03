package com.example.last.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import com.example.last.model.WeatherResponse
import com.example.last.api.WeatherAPIClient

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val apiClient = WeatherAPIClient()
    private val disposable = CompositeDisposable()

    val locationData = MutableLiveData<WeatherResponse>()
    val locationLoading = MutableLiveData<Boolean>()

    fun getWeatherDataWithGPS(latitude: String, longitude: String, units: String) {

        locationLoading.value = true

        disposable.add(
            apiClient.getDataFromGps(latitude, longitude, units)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherResponse>() {

                    override fun onSuccess(t: WeatherResponse) {
                        locationData.value = t
                        locationLoading.value = false
                        Log.i("state : ", "success")
                    }

                    override fun onError(e: Throwable) {
                        Log.i("state : ", "error" + e.message + " " + e.printStackTrace())
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
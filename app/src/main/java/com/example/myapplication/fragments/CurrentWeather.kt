package com.example.myapplication.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentCurrentWeatherBinding
import com.example.myapplication.model.CurrentResponseApi
import com.example.myapplication.viewmodel.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Response

class CurrentWeather : Fragment() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!
    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fusedLocationProviderClient = activity?.let {
            LocationServices.getFusedLocationProviderClient(
                it
            )
        }!!
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLocation()

    }


    private fun getLocation() {
        if(activity?.let {
                ContextCompat.checkSelfPermission(
                    it, Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED &&
            activity?.let {
                ContextCompat.checkSelfPermission(
                    it, Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED) {
            activity?.let { ActivityCompat.requestPermissions(it, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100) }
            return
        }

        val location = fusedLocationProviderClient.lastLocation

        var lat = 0
        var lon = 0


        location.addOnSuccessListener {
            binding.apply{

                var lat = it.latitude
                var lon = it.longitude
                var name = "London"
                Log.d("urrrr", "$lat, $lon")
                weatherViewModel.loadCurrentWeather(lat, lon, "metric").enqueue(object:
                    retrofit2.Callback<CurrentResponseApi> {
                    override fun onResponse(
                        call: Call<CurrentResponseApi>,
                        response: Response<CurrentResponseApi>
                    ) {
                        if(response.isSuccessful){
                            val data = response.body()
                            _binding?.test?.setText(data.toString())
                        }
                    }


                    override fun onFailure(call: Call<CurrentResponseApi>, t: Throwable) {
                        Toast.makeText(activity, "Helloworld", Toast.LENGTH_SHORT).show()
                    }
                }
                )


            }
        }


    }
}
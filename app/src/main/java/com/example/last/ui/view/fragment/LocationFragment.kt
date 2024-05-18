package com.example.last.ui.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.last.R
import com.example.last.databinding.FragmentLocationBinding
import com.example.last.model.network.WeatherApiClient
import com.example.last.model.repository.WeatherResponseRepository
import com.example.last.ui.viewmodel.LocationViewModel
import com.example.last.ui.viewmodel.LocationViewModelFactory
import com.example.last.util.Constant
import com.example.last.util.dateConverter
import com.example.last.util.getLocation
import com.example.last.util.timeConverter

class LocationFragment : Fragment() {

    private lateinit var viewModel: LocationViewModel
    private lateinit var dataBinding: FragmentLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = LocationViewModelFactory(
            repository = WeatherResponseRepository(WeatherApiClient.apiService),
            application = requireActivity().application
        )
        viewModel = ViewModelProvider(this, factory).get(LocationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_location,
            container,
            false
        )
        return dataBinding.root
    }

    @SuppressLint("DiscouragedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pair = getLocation(requireContext())

        // Assuming 'latitude' and 'longitude' are fetched and valid
        if (pair != null) {
            viewModel.getWeatherDataWithGPS(pair.first, pair.second, Constant.METRIC)
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.locationData.observe(viewLifecycleOwner, Observer { locationGps ->
            locationGps?.let {
                dataBinding.lytLocation.visibility = View.VISIBLE
                dataBinding.locationGPS = locationGps
                dataBinding.tvTemperature.text = it.main?.temp?.toInt().toString()
                dataBinding.tvDate.text = dateConverter()
                dataBinding.tvSunrise.text = it.sys?.sunrise?.let { it1 -> timeConverter(it1.toLong()) }
                dataBinding.tvSunset.text = it.sys?.sunset?.let { it1 -> timeConverter(it1.toLong()) }
                dataBinding.imgState.setImageResource(
                    resources.getIdentifier(
                        "ic_" + it.weather?.get(0)?.icon,
                        "drawable",
                        requireContext().packageName
                    )
                )
            }
        })

        viewModel.locationLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            dataBinding.locationLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
            dataBinding.lytLocation.visibility = if (isLoading) View.GONE else View.VISIBLE
        })
    }
}

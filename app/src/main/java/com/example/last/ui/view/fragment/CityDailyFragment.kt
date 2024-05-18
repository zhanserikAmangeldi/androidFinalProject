package com.example.last.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.last.R
import com.example.last.databinding.FragmentCityDailyBinding
import com.example.last.model.entity.CityDailyResponse
import com.example.last.model.network.WeatherApiClient
import com.example.last.model.network.WeatherApiInterface
import com.example.last.model.repository.CityDailyResponseRepository
import com.example.last.ui.adapter.CityDailyAdapter
import com.example.last.ui.viewmodel.CityDailyViewModel
import com.example.last.ui.viewmodel.CityDailyViewModelFactory
import com.example.last.util.Constant
import com.example.last.util.getLocation
import im.delight.android.location.SimpleLocation


class CityDailyFragment : Fragment() {

    private lateinit var viewModel : CityDailyViewModel
    private lateinit var dataBinding: FragmentCityDailyBinding

    private var cityDailyAdapter = CityDailyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = CityDailyViewModelFactory(
            repository = CityDailyResponseRepository(WeatherApiClient.apiService),
            application = requireActivity().application
        )
        viewModel = ViewModelProvider(this, factory).get(CityDailyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_city_daily, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        loadWeatherData()
    }

    private fun setupRecyclerView() {
        dataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cityDailyAdapter
        }
    }

    private fun setupObservers() {
        viewModel.cityDailyDataList.observe(viewLifecycleOwner, Observer { cityDailyWeatherGps ->
            cityDailyWeatherGps?.let {
                dataBinding.recyclerView.visibility = View.VISIBLE
                cityDailyAdapter.submitList(cityDailyWeatherGps)
            }
        })

        viewModel.cityDailyLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            dataBinding.cityDailyLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
            dataBinding.recyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
        })
    }

    private fun loadWeatherData() {
        val pair = getLocation(requireContext())

        // Assuming constants are defined for GPS coordinates
        if (pair != null) {
            viewModel.getCityDailyWeatherFromGps(pair.first, pair.second, Constant.CNT, Constant.METRIC)
        }
    }
}

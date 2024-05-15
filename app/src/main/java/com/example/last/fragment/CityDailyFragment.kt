package com.example.last.fragment

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
import com.example.last.util.Constant
import com.example.last.viewmodel.CityDailyViewModel
import com.example.last.adapter.CityDailyAdapter
import com.example.last.util.Constant.latitude
import com.example.last.util.Constant.longitude
import im.delight.android.location.SimpleLocation
import androidx.navigation.fragment.findNavController
import android.content.Context
import com.example.last.PreferenceManager


class CityDailyFragment : Fragment() {


    var location: SimpleLocation? = null

    private lateinit var viewModel : CityDailyViewModel
    private lateinit var dataBinding: FragmentCityDailyBinding

    private var cityDailyAdapter = CityDailyAdapter(arrayListOf())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        dataBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        dataBinding.recyclerView.adapter = cityDailyAdapter

        viewModel = ViewModelProvider(this).get(CityDailyViewModel::class.java)

        viewModel.getCityDailyWeatherFromGps(latitude,longitude, Constant.CNT,Constant.METRIC)

        viewModel.cityDailyData.observe(viewLifecycleOwner, Observer {cityDailyWeatherGps ->
            cityDailyWeatherGps.let {
                dataBinding.recyclerView.visibility = View.VISIBLE
                cityDailyAdapter.updateCountryList(cityDailyWeatherGps)
            }
        })

        viewModel.cityDailyLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it){
                    dataBinding.cityDailyLoading.visibility = View.VISIBLE
                    dataBinding.recyclerView.visibility = View.GONE
                }else{
                    dataBinding.cityDailyLoading.visibility = View.GONE
                }
            }
        })

    }



}

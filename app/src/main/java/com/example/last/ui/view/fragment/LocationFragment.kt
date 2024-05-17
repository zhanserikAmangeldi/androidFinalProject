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
import com.example.last.util.Constant
import com.example.last.util.Constant.latitude
import com.example.last.util.Constant.longitude
import com.example.last.util.dateConverter
import com.example.last.util.timeConverter
import com.example.last.ui.viewmodel.LocationViewModel

class LocationFragment : Fragment() {

    private lateinit var viewModel: LocationViewModel
    private lateinit var dataBinding: FragmentLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        viewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        viewModel.getWeatherDataWithGPS(latitude, longitude, Constant.METRIC)


        viewModel.locationData.observe(viewLifecycleOwner, Observer { locationGps ->
            locationGps?.let {

                dataBinding.lytLocation.visibility = View.VISIBLE

                dataBinding.locationGPS = locationGps

                dataBinding.tvTemperature.text = locationGps.main!!.temp.toInt().toString()

                dataBinding.tvDate.text = dateConverter()

                dataBinding.tvSunrise.text = timeConverter((locationGps.sys!!.sunrise).toLong())

                dataBinding.tvSunset.text = timeConverter((locationGps.sys!!.sunset).toLong())

                dataBinding.imgState.setImageResource(
                    resources.getIdentifier(
                        "ic_"+locationGps.weather?.get(0)?.icon,
                        "drawable",
                        view.context.packageName)
                )

            }
        })

        viewModel.locationLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it){
                    dataBinding.locationLoading.visibility = View.VISIBLE
                    dataBinding.lytLocation.visibility = View.GONE
                }else{
                    dataBinding.locationLoading.visibility = View.GONE
                }
            }
        })



    }

}

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
import com.example.last.databinding.FragmentFiveDaysBinding
import com.example.last.util.Constant
import com.example.last.viewmodel.FiveDaysViewModel
import com.example.last.adapter.HourlyAdapter
import com.example.last.util.Constant.latitude
import com.example.last.util.Constant.longitude
import im.delight.android.location.SimpleLocation


class FiveDaysFragment : Fragment() {

    var location: SimpleLocation? = null

    private lateinit var viewModel: FiveDaysViewModel
    private val hourlyAdapter = HourlyAdapter(arrayListOf())
    private lateinit var dataBinding: FragmentFiveDaysBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_five_days, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this).get(FiveDaysViewModel::class.java)

        dataBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        dataBinding.recyclerView.adapter = hourlyAdapter

        viewModel.getForecastFromGps(latitude, longitude, Constant.METRIC)

        viewModel.forecastData.observe(viewLifecycleOwner, Observer { forecastGps ->
            forecastGps?.let {
                dataBinding.crdFiveDays.visibility = View.VISIBLE
                hourlyAdapter.updateHourlyList(forecastGps)
            }
        })

        viewModel.fiveDaysLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it){
                   dataBinding.fiveDaysLoading.visibility = View.VISIBLE
                   dataBinding.crdFiveDays.visibility = View.GONE
                }else{
                    dataBinding.fiveDaysLoading.visibility = View.GONE
                }
            }

        })

    }


}

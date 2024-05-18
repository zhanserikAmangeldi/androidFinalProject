package com.example.last.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.last.R
import com.example.last.databinding.FragmentOneDayDetailBinding
import com.example.last.model.entity.CityDailyForecast
import com.example.last.model.entity.CityDailyResponse


class CityDailyDetailFragment : Fragment() {

    private lateinit var dataBinding: FragmentOneDayDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_one_day_detail,
                container,
                false,
            )
        val cityDailyResponse = requireArguments().getParcelable<CityDailyForecast>("cityWeatherDetail")
        dataBinding.detail = cityDailyResponse
        return dataBinding.root
    }



}

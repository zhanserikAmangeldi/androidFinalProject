package com.example.last.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.last.R
import com.example.last.databinding.FragmentOneDayDetailBinding
import com.example.last.model.CityDailyResponse
import android.widget.Button
import androidx.navigation.fragment.findNavController


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
        val cityDailyResponse = arguments?.getParcelable<CityDailyResponse.Forecast>("cityWeatherDetail")
        dataBinding.detail = cityDailyResponse
        return dataBinding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup the back button
        dataBinding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}

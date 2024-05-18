package com.example.last.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.last.R
import com.example.last.databinding.ForecastWeatherDailyItemBinding
import com.example.last.model.entity.CityDailyForecast
import com.example.last.util.CityDailyDiffUtil


class CityDailyAdapter : ListAdapter<CityDailyForecast,CityDailyAdapter.CityDailyViewHolder>(
    CityDailyDiffUtil()
) {

    class CityDailyViewHolder(var view: ForecastWeatherDailyItemBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityDailyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ForecastWeatherDailyItemBinding>(
            inflater,
            R.layout.forecast_weather_daily_item,
            parent,
            false
        )
        return CityDailyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityDailyViewHolder, position: Int) {
        val cityDaily = getItem(position)

        holder.view.dailyCityWeather = cityDaily

        holder.view.tvCityTemp.text = cityDaily.main!!.temp.toInt().toString()

        holder.itemView.setOnClickListener {
            val bundle = bundleOf("cityWeatherDetail" to cityDaily)
            Navigation.findNavController(it).navigate(R.id.action_oneDayFragment_to_oneDayDetailFragment2, bundle)
        }
    }

}
package com.example.last.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.last.R
import com.example.last.databinding.ForecastWeatherHourlyItemBinding
import com.example.last.model.entity.Forecast
import com.example.last.util.ForecastDiffUtil
import com.example.last.util.dayConverter

class HourlyAdapter : ListAdapter<Forecast, HourlyAdapter.HourlyViewHolder>(ForecastDiffUtil()) {

    class HourlyViewHolder(var view: ForecastWeatherHourlyItemBinding) :
        RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ForecastWeatherHourlyItemBinding>(
            inflater,
            R.layout.forecast_weather_hourly_item,
            parent,
            false
        )
        return HourlyViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {

        val hourly = getItem(position)

        holder.view.forecast = hourly

        holder.view.tvForecastTime.text = dayConverter((hourly.dt).toLong())
        holder.view.tvForecastTemp.text = hourly.main!!.temp.toInt().toString()
        holder.view.tvForecastFeelsTemp.text = hourly.main!!.feelsLike.toInt().toString()
    }

}

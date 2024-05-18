package com.example.last.util

import androidx.recyclerview.widget.DiffUtil
import com.example.last.model.entity.CityDailyForecast

class CityDailyDiffUtil: DiffUtil.ItemCallback<CityDailyForecast>() {
    override fun areItemsTheSame(oldItem: CityDailyForecast, newItem: CityDailyForecast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CityDailyForecast, newItem: CityDailyForecast): Boolean {
        return oldItem == newItem
    }
}
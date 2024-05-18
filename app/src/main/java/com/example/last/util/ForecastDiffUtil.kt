package com.example.last.util

import androidx.recyclerview.widget.DiffUtil
import com.example.last.model.entity.Forecast

class ForecastDiffUtil: DiffUtil.ItemCallback<Forecast>() {
    override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem == newItem
    }
}
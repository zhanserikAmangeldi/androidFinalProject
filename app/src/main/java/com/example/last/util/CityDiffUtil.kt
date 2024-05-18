package com.example.last.util

import androidx.recyclerview.widget.DiffUtil
import com.example.last.model.entity.CityResponse

class CityDiffUtil: DiffUtil.ItemCallback<CityResponse>() {
    override fun areItemsTheSame(oldItem: CityResponse, newItem: CityResponse): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CityResponse, newItem: CityResponse): Boolean {
        return oldItem == newItem
    }
}
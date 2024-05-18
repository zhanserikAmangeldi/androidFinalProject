package com.example.last.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.last.R
import com.example.last.databinding.CityItemBinding
import com.example.last.model.entity.CityResponse
import com.example.last.util.CityDiffUtil

class CityAdapter : ListAdapter<CityResponse,CityAdapter.CityViewHolder>(
    CityDiffUtil()
) {
    class CityViewHolder(var view: CityItemBinding) :
        RecyclerView.ViewHolder(view.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CityItemBinding>(
            inflater,
            R.layout.city_item,
            parent,
            false
        )
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = getItem(position)

        holder.view.txtCityName.text = city.name
        holder.view.txtLatitude.text = city.latitude.toString()
        holder.view.txtLongitude.text = city.longitude.toString()
        holder.view.txtCountry.text = city.country

        holder.itemView.setOnClickListener {
            saveLocation(holder.itemView.context, city.latitude, city.longitude)
        }
    }

    fun saveLocation(context: Context, lat: Double, lon: Double){
        val sharedPreferences = context.getSharedPreferences("AppSettingsPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat("latitude", lat.toFloat())
        editor.putFloat("longitude", lon.toFloat())
        editor.apply()
    }
}
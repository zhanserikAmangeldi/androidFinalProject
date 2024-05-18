package com.example.last.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.last.R
import com.example.last.databinding.FragmentSettingsBinding
import com.example.last.model.network.GeoApiClient
import com.example.last.model.repository.CityResponseRepository
import com.example.last.ui.adapter.CityAdapter
import com.example.last.ui.viewmodel.CityViewModel
import com.example.last.ui.viewmodel.CityViewModelFactory


class SettingsFragment : Fragment() {

    private lateinit var dataBinding: FragmentSettingsBinding
    private lateinit var viewModel: CityViewModel
    private var cityAdapter = CityAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = CityViewModelFactory(
            repository = CityResponseRepository(GeoApiClient.apiService),
            application = requireActivity().application
        )
        viewModel = ViewModelProvider(this, factory).get(CityViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearch()
        setupObservers()
    }

    private fun setupRecyclerView() {
        dataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cityAdapter
        }
    }

    private fun setupSearch(){
        dataBinding.btnSearch.setOnClickListener{
            val searchQuery = dataBinding.etSearch.text.toString()
            if(searchQuery.isNotEmpty())
                viewModel.getCoordinatesByCity(searchQuery)
            else
                Toast.makeText(context, "Please enter a search term", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupObservers() {
        viewModel.cityData.observe(viewLifecycleOwner, Observer { cityList ->
            cityList?.let {
                cityAdapter.submitList(it) // Update adapter's list
            }
        })
    }
}
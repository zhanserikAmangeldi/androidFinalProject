package com.example.last.ui.view.fragment

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
import com.example.last.model.network.WeatherApiClient
import com.example.last.model.repository.ForecastResponseRepository
import com.example.last.ui.adapter.HourlyAdapter
import com.example.last.ui.viewmodel.FiveDaysViewModel
import com.example.last.ui.viewmodel.FiveDaysViewModelFactory
import com.example.last.util.Constant
import com.example.last.util.getLocation
import im.delight.android.location.SimpleLocation


class FiveDaysFragment : Fragment() {

    var location: SimpleLocation? = null

    private lateinit var viewModel: FiveDaysViewModel
    private val hourlyAdapter = HourlyAdapter()
    private lateinit var dataBinding: FragmentFiveDaysBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = FiveDaysViewModelFactory(
            repository = ForecastResponseRepository(WeatherApiClient.apiService),
            application = requireActivity().application
        )
        viewModel = ViewModelProvider(this, factory).get(FiveDaysViewModel::class.java)
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
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        dataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = hourlyAdapter
        }
    }

    private fun observeViewModel() {
        val pair = getLocation(requireContext())

        if (pair != null) {
            viewModel.getForecastFromGps(pair.first, pair.second, Constant.METRIC)
        }

        viewModel.forecastDataList.observe(viewLifecycleOwner, Observer { forecastGps ->
            forecastGps?.let {
                dataBinding.crdFiveDays.visibility = View.VISIBLE
                hourlyAdapter.submitList(it)
            }
        })

        viewModel.fiveDaysLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            dataBinding.fiveDaysLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
            dataBinding.crdFiveDays.visibility = if (isLoading) View.GONE else View.VISIBLE
        })
    }

}

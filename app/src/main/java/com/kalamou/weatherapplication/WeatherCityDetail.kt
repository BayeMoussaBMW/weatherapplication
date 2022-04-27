package com.kalamou.weatherapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kalamou.weatherapplication.api.NetworkServices
import com.kalamou.weatherapplication.databinding.FragmentWeatherCityDetailBinding

class WeatherCityDetail : Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels {
        WeatherViewModelFactory(
            networkServices = NetworkServices(),
            (activity?.application as WeatherApplication).database.itemNameDao(),
            (activity?.application as WeatherApplication).database.dataDao()
        )
    }

    private var _binding: FragmentWeatherCityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWeatherCityDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.data.observe(this.viewLifecycleOwner) {
            viewModel.getData(it!!.id)
            binding.name.text = it.name
            binding.temperature.text = it.main?.temp.toString().plus(" °C")
        }
    }

}
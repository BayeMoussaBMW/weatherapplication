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

            it.let {
                binding.imv.setImageResource(
                    when (it.weather?.get(0)?.icon.toString()) {
                        "01d" -> R.drawable.a01d
                        "01n" -> R.drawable.b01n
                        "02d" -> R.drawable.c02d
                        "02n" -> R.drawable.d02n
                        "03d" -> R.drawable.e03d
                        "03n" -> R.drawable.f03n
                        "04d" -> R.drawable.g04d
                        "04n" -> R.drawable.h04n
                        "09d" -> R.drawable.i09d
                        "09n" -> R.drawable.j09n
                        "10d" -> R.drawable.k10d
                        "10n" -> R.drawable.l10n
                        "11d" -> R.drawable.m11d
                        "11n" -> R.drawable.n11n
                        "13d" -> R.drawable.o13d
                        "13n" -> R.drawable.p13n
                        "50d" -> R.drawable.q50d
                        "50n" -> R.drawable.u50n
                        else -> R.drawable.c02d
                    }
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.kalamou.weatherapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kalamou.weatherapplication.api.NetworkServices
import com.kalamou.weatherapplication.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels {
        WeatherViewModelFactory(
            networkServices = NetworkServices(),
            (activity?.application as WeatherApplication).database.itemNameDao(),
            (activity?.application as WeatherApplication).database.dataDao()
        )
    }

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WeatherAdapter {
            val data = viewModel.getDataToDisplay(it.name)
            if (data != null) {
                viewModel.insertNewData(data)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_weatherCityDetail)
        }

        binding.recyclerView.adapter = adapter
        viewModel.itemNames.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
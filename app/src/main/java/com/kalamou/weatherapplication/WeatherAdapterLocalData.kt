package com.kalamou.weatherapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kalamou.weatherapplication.databinding.ItemListBinding
import com.kalamou.weatherapplication.model.Data

class WeatherAdapterLocalData(
    private val onItemClicked: (Data) -> Unit
) :
    ListAdapter<Data, WeatherAdapterLocalData.WeatherViewHolder>(DiffCallback) {

    class WeatherViewHolder(private var binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(current: Data) {
            binding.apply {
                nameId.text = current.name
                temperature.text = data?.main?.temp.toString().plus(" °C")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

}
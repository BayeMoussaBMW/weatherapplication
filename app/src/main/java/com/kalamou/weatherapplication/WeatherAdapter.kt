package com.kalamou.weatherapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kalamou.weatherapplication.databinding.ItemListBinding
import com.kalamou.weatherapplication.db.model.ItemName


class WeatherAdapter(
    private val onItemClicked: (ItemName) -> Unit) :
    ListAdapter<ItemName, WeatherAdapter.WeatherViewHolder>(DiffCallback) {

    class WeatherViewHolder(private var binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(current: ItemName) {
            binding.apply {
                nameId.text = current.name
                temperature.text = "-- °C"
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
        private val DiffCallback = object : DiffUtil.ItemCallback<ItemName>() {
            override fun areItemsTheSame(oldItem: ItemName, newItem: ItemName): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ItemName, newItem: ItemName): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

}

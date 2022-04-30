package com.kalamou.weatherapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Dao
import com.kalamou.weatherapplication.databinding.ItemListBinding
import com.kalamou.weatherapplication.db.DataDao
import com.kalamou.weatherapplication.db.model.ItemName
import com.kalamou.weatherapplication.model.Data


class WeatherAdapter(
    private val onItemClicked: (ItemName) -> Unit) :
    ListAdapter<ItemName, WeatherAdapter.WeatherViewHolder>(DiffCallback) {

    class WeatherViewHolder(private var binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(current: ItemName, data: Data) {
            binding.apply {
                nameId.text = current.name
                //temperature.text = data.main?.temp.toString().plus(" °C")
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
        val data = Data()
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current, data)
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

package sodikov.weather_simple.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sodikov.weather_simple.R
import sodikov.weather_simple.databinding.ItemWeatherBinding
import sodikov.weather_simple.model.Weather

class WeatherAdapter : ListAdapter<Weather, WeatherAdapter.WeatherViewHolder>(Comparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemWeatherBinding.bind(view)
        fun bind(model: Weather) = with(binding) {
            dataTV.text = model.time
            conditionTV.text = model.condition
            currentTempTV.text = model.currentTemp
        }
    }

    class Comparator : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }
    }
}
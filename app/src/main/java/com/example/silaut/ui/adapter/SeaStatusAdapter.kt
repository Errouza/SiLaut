package com.example.silaut.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.silaut.data.model.SeaStatus_018
import com.example.silaut.databinding.ItemSeaStatusBinding

class SeaStatusAdapter(private var list_018: List<SeaStatus_018>) : 
    RecyclerView.Adapter<SeaStatusAdapter.ViewHolder>() {

    class ViewHolder(val binding_018: ItemSeaStatusBinding) : RecyclerView.ViewHolder(binding_018.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding_018 = ItemSeaStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding_018)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item_018 = list_018[position]
        holder.binding_018.apply {
            tvLocationName.text = item_018.locationName_018
            tvSeaCondition.text = item_018.condition_018
            tvCurrent.text = item_018.currentSpeed_018
            tvWind.text = item_018.windDirection_018
            tvWindSpeed.text = item_018.windSpeed_018
            tvTemp.text = item_018.temperature_018
        }
    }

    override fun getItemCount(): Int = list_018.size

    fun updateData(newList_018: List<SeaStatus_018>) {
        list_018 = newList_018
        notifyDataSetChanged()
    }
}

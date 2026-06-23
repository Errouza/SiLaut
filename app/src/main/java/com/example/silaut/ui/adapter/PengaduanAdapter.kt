package com.example.silaut.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.silaut.R
import com.example.silaut.data.model.Pengaduan_018
import com.example.silaut.databinding.ItemPengaduanBinding
import java.text.SimpleDateFormat
import java.util.*

class PengaduanAdapter(private val list_018: List<Pengaduan_018>) : 
    RecyclerView.Adapter<PengaduanAdapter.ViewHolder>() {

    class ViewHolder(val binding_018: ItemPengaduanBinding) : RecyclerView.ViewHolder(binding_018.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding_018 = ItemPengaduanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding_018)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item_018 = list_018[position]
        holder.binding_018.apply {
            tvItemJudul.text = item_018.judul_laporan_018
            tvItemTanggal.text = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(item_018.tanggal_018))
            chipStatus.text = item_018.status_laporan_018
            
            // Set chip color based on status
            val colorRes_018 = when(item_018.status_laporan_018.lowercase()) {
                "pending" -> R.color.status_pending
                "diproses" -> R.color.status_process
                "selesai" -> R.color.status_success
                else -> R.color.sea_blue_primary
            }
            chipStatus.setChipBackgroundColorResource(colorRes_018)
        }
    }

    override fun getItemCount(): Int = list_018.size
}

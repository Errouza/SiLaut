package com.example.silaut.ui.pengaduan

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silaut.data.local.AppDatabase_018
import com.example.silaut.data.local.PreferenceManager_018
import com.example.silaut.databinding.ActivityStatusLaporanBinding
import com.example.silaut.ui.adapter.PengaduanAdapter
import kotlinx.coroutines.launch

class StatusLaporanActivity_018 : AppCompatActivity() {

    private lateinit var binding_018: ActivityStatusLaporanBinding
    private val db_018 by lazy { AppDatabase_018.getDatabase_018(this) }
    private val pref_018 by lazy { PreferenceManager_018(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_018 = ActivityStatusLaporanBinding.inflate(layoutInflater)
        setContentView(binding_018.root)

        setupToolbar_018()
        setupRecyclerView_018()
    }

    private fun setupToolbar_018() {
        binding_018.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupRecyclerView_018() {
        binding_018.rvStatusLaporan.layoutManager = LinearLayoutManager(this)
        
        val userId_018 = pref_018.getUserId_018()
        
        lifecycleScope.launch {
            db_018.pengaduanDao_018().getPengaduanByUser_018(userId_018).collect { list_018 ->
                if (list_018.isEmpty()) {
                    binding_018.layoutEmpty.visibility = View.VISIBLE
                    binding_018.rvStatusLaporan.visibility = View.GONE
                } else {
                    binding_018.layoutEmpty.visibility = View.GONE
                    binding_018.rvStatusLaporan.visibility = View.VISIBLE
                    binding_018.rvStatusLaporan.adapter = PengaduanAdapter(list_018)
                }
            }
        }
    }
}

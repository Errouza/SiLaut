package com.example.silaut

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silaut.data.local.AppDatabase_018
import com.example.silaut.data.local.PreferenceManager_018
import com.example.silaut.data.model.SeaStatus_018
import com.example.silaut.databinding.ActivityMainBinding
import com.example.silaut.ui.adapter.PengaduanAdapter
import com.example.silaut.ui.adapter.SeaStatusAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding_018: ActivityMainBinding
    private val pref_018 by lazy { PreferenceManager_018(this) }
    private val db_018 by lazy { AppDatabase_018.getDatabase_018(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_018 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding_018.root)

        loadUserData_018()
        setupQuickActions_018()
        setupRecyclerView_018()
        setupSeaStatus_018()
        setupSearch_018()
        
        binding_018.btnLogout.setOnClickListener {
            pref_018.logout_018()
            startActivity(android.content.Intent(this, com.example.silaut.ui.auth.LoginActivity::class.java))
            finish()
        }
    }

    private val seaStatusList_018 = listOf(
        SeaStatus_018("Pantai Kuta, Bali", "Cerah | Gelombang Rendah", "0.5 m/s", "Barat Laut", "12 knots", "28°C"),
        SeaStatus_018("Ancol, Jakarta", "Berawan | Gelombang Tenang", "0.3 m/s", "Utara", "8 knots", "29°C"),
        SeaStatus_018("Pangandaran, Jabar", "Cerah | Gelombang Sedang", "0.8 m/s", "Selatan", "15 knots", "27°C")
    )
    private lateinit var seaAdapter_018: SeaStatusAdapter

    private fun setupSeaStatus_018() {
        seaAdapter_018 = SeaStatusAdapter(seaStatusList_018)
        binding_018.rvSeaStatus.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = seaAdapter_018
        }
    }

    private fun setupSearch_018() {
        binding_018.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH) {
                val query_018 = binding_018.etSearch.text.toString().lowercase()
                val filteredList_018 = seaStatusList_018.filter { it.locationName_018.lowercase().contains(query_018) }
                if (filteredList_018.isNotEmpty()) {
                    seaAdapter_018.updateData(filteredList_018)
                } else {
                    Toast.makeText(this, "Lokasi tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
                true
            } else {
                false
            }
        }
    }

    private fun loadUserData_018() {
        val userId_018 = pref_018.getUserId_018()
        lifecycleScope.launch {
            db_018.userDao_018().getUserById_018(userId_018).collect { user_018 ->
                user_018?.let {
                    binding_018.tvGreeting.text = "Halo, ${it.nama_018}"
                    if (it.role_018 == "ADMIN") {
                        binding_018.tvWeather.text = "Mode Admin: Kelola Laporan Masuk"
                    }
                }
            }
        }
    }

    private fun setupQuickActions_018() {
        binding_018.cardBuatPengaduan.setOnClickListener {
            val intent_018 = android.content.Intent(this, com.example.silaut.ui.pengaduan.BuatPengaduanActivity::class.java)
            startActivity(intent_018)
        }
        binding_018.cardStatusLaporan.setOnClickListener {
            val intent_018 = android.content.Intent(this, com.example.silaut.ui.pengaduan.StatusLaporanActivity_018::class.java)
            startActivity(intent_018)
        }
        binding_018.cardPusatBantuan.setOnClickListener {
            val intent_018 = android.content.Intent(this, com.example.silaut.ui.chat.ChatActivity::class.java)
            startActivity(intent_018)
        }
    }

    private fun setupRecyclerView_018() {
        binding_018.rvRecentPengaduan.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            db_018.pengaduanDao_018().getAllPengaduan_018().collect { list_018 ->
                binding_018.rvRecentPengaduan.adapter = PengaduanAdapter(list_018)
            }
        }
    }
}

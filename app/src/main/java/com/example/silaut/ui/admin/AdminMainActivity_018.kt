package com.example.silaut.ui.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silaut.data.local.AppDatabase_018
import com.example.silaut.data.local.PreferenceManager_018
import com.example.silaut.data.model.Pengaduan_018
import com.example.silaut.databinding.ActivityAdminMainBinding
import com.example.silaut.ui.adapter.AdminPengaduanAdapter_018
import com.example.silaut.ui.auth.LoginActivity
import kotlinx.coroutines.launch

class AdminMainActivity_018 : AppCompatActivity() {

    private lateinit var binding_018: ActivityAdminMainBinding
    private val db_018 by lazy { AppDatabase_018.getDatabase_018(this) }
    private val pref_018 by lazy { PreferenceManager_018(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_018 = ActivityAdminMainBinding.inflate(layoutInflater)
        setContentView(binding_018.root)

        setupRecyclerView_018()
        setupLogout_018()
        setupChatEntry_018()
    }

    private fun setupChatEntry_018() {
        binding_018.cardAdminChat.setOnClickListener {
            startActivity(Intent(this, AdminChatListActivity_018::class.java))
        }
    }

    private fun setupRecyclerView_018() {
        binding_018.rvAdminPengaduan.layoutManager = LinearLayoutManager(this)
        
        lifecycleScope.launch {
            db_018.pengaduanDao_018().getAllPengaduan_018().collect { list_018 ->
                binding_018.tvTotalLaporanCount.text = list_018.size.toString()
                
                binding_018.rvAdminPengaduan.adapter = AdminPengaduanAdapter_018(list_018) { pengaduan_018 ->
                    showUpdateStatusDialog_018(pengaduan_018)
                }
            }
        }
    }

    private fun showUpdateStatusDialog_018(pengaduan_018: Pengaduan_018) {
        val statuses_018 = arrayOf("Pending", "Diproses", "Selesai")
        AlertDialog.Builder(this)
            .setTitle("Update Status Laporan")
            .setItems(statuses_018) { _, which ->
                val newStatus_018 = statuses_018[which]
                updateStatus_018(pengaduan_018, newStatus_018)
            }
            .show()
    }

    private fun updateStatus_018(pengaduan_018: Pengaduan_018, newStatus_018: String) {
        val updatedPengaduan_018 = pengaduan_018.copy(status_laporan_018 = newStatus_018)
        lifecycleScope.launch {
            db_018.pengaduanDao_018().updatePengaduan_018(updatedPengaduan_018)
            Toast.makeText(this@AdminMainActivity_018, "Status diperbarui menjadi $newStatus_018", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupLogout_018() {
        binding_018.btnLogoutAdmin.setOnClickListener {
            pref_018.logout_018()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}

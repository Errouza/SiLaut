package com.example.silaut.ui.pengaduan

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.silaut.data.local.AppDatabase_018
import com.example.silaut.data.local.PreferenceManager_018
import com.example.silaut.data.model.Pengaduan_018
import com.example.silaut.databinding.ActivityBuatPengaduanBinding
import kotlinx.coroutines.launch

class BuatPengaduanActivity : AppCompatActivity() {

    private lateinit var binding_018: ActivityBuatPengaduanBinding
    private val db_018 by lazy { AppDatabase_018.getDatabase_018(this) }
    private val pref_018 by lazy { PreferenceManager_018(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_018 = ActivityBuatPengaduanBinding.inflate(layoutInflater)
        setContentView(binding_018.root)

        setupToolbar_018()
        setupDropdownKategori_018()
        setupListeners_018()
    }

    private fun setupToolbar_018() {
        binding_018.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupDropdownKategori_018() {
        val kategoriList_018 = listOf("Pencemaran", "Cuaca", "Keamanan", "Kecelakaan", "Lainnya")
        val adapter_018 = ArrayAdapter(this, android.R.layout.simple_list_item_1, kategoriList_018)
        binding_018.actKategori.setAdapter(adapter_018)
    }

    private fun setupListeners_018() {
        binding_018.btnUploadFoto.setOnClickListener {
            Toast.makeText(this, "Membuka Kamera/Galeri...", Toast.LENGTH_SHORT).show()
        }

        binding_018.btnKirim.setOnClickListener {
            validateAndSave_018()
        }
    }

    private fun validateAndSave_018() {
        val judul_018 = binding_018.etJudul.text.toString().trim()
        val kategori_018 = binding_018.actKategori.text.toString().trim()
        val deskripsi_018 = binding_018.etDeskripsi.text.toString().trim()

        if (judul_018.isEmpty()) {
            binding_018.tilJudul.error = "Judul tidak boleh kosong"
            return
        }
        if (kategori_018.isEmpty()) {
            binding_018.tilKategori.error = "Pilih kategori terlebih dahulu"
            return
        }
        if (deskripsi_018.isEmpty()) {
            binding_018.tilDeskripsi.error = "Deskripsi tidak boleh kosong"
            return
        }

        binding_018.tilJudul.error = null
        binding_018.tilKategori.error = null
        binding_018.tilDeskripsi.error = null

        saveToDatabase_018(judul_018, kategori_018, deskripsi_018)
    }

    private fun saveToDatabase_018(judul_018: String, kategori_018: String, deskripsi_018: String) {
        val pengaduan_018 = Pengaduan_018(
            userId_018 = pref_018.getUserId_018(),
            judul_laporan_018 = judul_018,
            kategori_018 = kategori_018,
            deskripsi_018 = deskripsi_018,
            status_laporan_018 = "Pending"
        )

        lifecycleScope.launch {
            try {
                db_018.pengaduanDao_018().insertPengaduan_018(pengaduan_018)
                Toast.makeText(this@BuatPengaduanActivity, "Laporan Berhasil Terkirim!", Toast.LENGTH_LONG).show()
                finish()
            } catch (e: Exception) {
                Toast.makeText(this@BuatPengaduanActivity, "Gagal mengirim: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

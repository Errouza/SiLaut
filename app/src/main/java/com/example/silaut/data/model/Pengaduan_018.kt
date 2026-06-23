package com.example.silaut.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pengaduan_018",
    foreignKeys = [
        ForeignKey(
            entity = User_018::class,
            parentColumns = ["id_018"],
            childColumns = ["userId_018"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId_018"])]
)
data class Pengaduan_018(
    @PrimaryKey(autoGenerate = true) val id_018: Int = 0,
    val userId_018: Int,
    val judul_laporan_018: String,
    val kategori_018: String, // pencemaran, cuaca, keamanan, dll
    val deskripsi_018: String,
    val tanggal_018: Long = System.currentTimeMillis(),
    val status_laporan_018: String = "Pending" // Pending, Diproses, Selesai
)

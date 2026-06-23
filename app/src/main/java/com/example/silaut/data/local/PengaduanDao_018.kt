package com.example.silaut.data.local

import androidx.room.*
import com.example.silaut.data.model.Pengaduan_018
import kotlinx.coroutines.flow.Flow

@Dao
interface PengaduanDao_018 {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPengaduan_018(pengaduan_018: Pengaduan_018)

    @Update
    suspend fun updatePengaduan_018(pengaduan_018: Pengaduan_018)

    @Delete
    suspend fun deletePengaduan_018(pengaduan_018: Pengaduan_018)

    @Query("SELECT * FROM pengaduan_018 ORDER BY tanggal_018 DESC")
    fun getAllPengaduan_018(): Flow<List<Pengaduan_018>>

    @Query("SELECT * FROM pengaduan_018 WHERE userId_018 = :userId_018 ORDER BY tanggal_018 DESC")
    fun getPengaduanByUser_018(userId_018: Int): Flow<List<Pengaduan_018>>
}

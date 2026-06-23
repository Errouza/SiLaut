package com.example.silaut.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_018")
data class User_018(
    @PrimaryKey(autoGenerate = true) val id_018: Int = 0,
    val nama_018: String,
    val email_018: String,
    val password_018: String,
    val role_018: String = "USER" // "USER" or "ADMIN"
)

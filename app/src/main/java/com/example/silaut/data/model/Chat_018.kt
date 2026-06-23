package com.example.silaut.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "chats_018",
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
data class Chat_018(
    @PrimaryKey(autoGenerate = true) val id_018: Int = 0,
    val userId_018: Int,
    val pesan_018: String,
    val timestamp_018: Long = System.currentTimeMillis(),
    val isFromAdmin_018: Boolean
)

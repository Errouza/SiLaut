package com.example.silaut.data.local

import androidx.room.*
import com.example.silaut.data.model.Chat_018
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao_018 {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat_018(chat_018: Chat_018)

    @Query("SELECT * FROM chats_018 WHERE userId_018 = :userId_018 ORDER BY timestamp_018 ASC")
    fun getChatHistory_018(userId_018: Int): Flow<List<Chat_018>>

    @Query("SELECT DISTINCT userId_018 FROM chats_018")
    fun getUsersWithChats_018(): Flow<List<Int>>
}

package com.example.silaut.data.local

import androidx.room.*
import com.example.silaut.data.model.User_018
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao_018 {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser_018(user_018: User_018): Long

    @Update
    suspend fun updateUser_018(user_018: User_018)

    @Delete
    suspend fun deleteUser_018(user_018: User_018)

    @Query("SELECT * FROM users_018 WHERE email_018 = :email_018 AND password_018 = :password_018 LIMIT 1")
    suspend fun login_018(email_018: String, password_018: String): User_018?

    @Query("SELECT * FROM users_018 WHERE email_018 = :email_018 LIMIT 1")
    suspend fun getUserByEmail_018(email_018: String): User_018?

    @Query("SELECT * FROM users_018 WHERE id_018 = :id_018")
    fun getUserById_018(id_018: Int): Flow<User_018?>

    @Query("SELECT * FROM users_018 WHERE id_018 IN (:ids_018)")
    suspend fun getUsersByIds_018(ids_018: List<Int>): List<User_018>
}

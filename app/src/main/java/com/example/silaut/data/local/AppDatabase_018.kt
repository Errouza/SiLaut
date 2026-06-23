package com.example.silaut.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.silaut.data.model.Chat_018
import com.example.silaut.data.model.Pengaduan_018
import com.example.silaut.data.model.User_018

@Database(
    entities = [User_018::class, Pengaduan_018::class, Chat_018::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase_018 : RoomDatabase() {
    abstract fun userDao_018(): UserDao_018
    abstract fun pengaduanDao_018(): PengaduanDao_018
    abstract fun chatDao_018(): ChatDao_018

    companion object {
        @Volatile
        private var INSTANCE_018: AppDatabase_018? = null

        fun getDatabase_018(context: Context): AppDatabase_018 {
            return INSTANCE_018 ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase_018::class.java,
                    "silaut_database_018"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE_018 = instance
                instance
            }
        }
    }
}

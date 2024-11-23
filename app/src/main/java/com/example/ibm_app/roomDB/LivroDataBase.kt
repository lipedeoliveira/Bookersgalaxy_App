package com.example.ibm_app.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [Livro::class],
    version = 1
)

abstract class LivroDataBase : RoomDatabase() {
    abstract fun livroDao():LivroDao

    companion object {
        @Volatile
        private var INSTANCE: LivroDataBase? = null

        fun getInstance(context: Context):LivroDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LivroDataBase::class.java,
                    "livro_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}
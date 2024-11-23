package com.example.ibm_app.roomDB

import android.app.people.PeopleManager
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface LivroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertLivro(livro: Livro)

    @Delete
    suspend fun deleteLivro(livro: Livro)

    @Query("SELECT * FROM Livro")
    fun getAllLivro(): Flow<List<Livro>>

}
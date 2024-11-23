package com.example.ibm_app.viewModel

import com.example.ibm_app.roomDB.Livro
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun upsertLivro(livro: Livro)
    suspend fun deleteLivro(livro: Livro)
    fun getAllLivros(): Flow<List<Livro>>
}
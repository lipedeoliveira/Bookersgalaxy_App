package com.example.ibm_app.viewModel

import com.example.ibm_app.roomDB.Livro
import com.example.ibm_app.roomDB.LivroDataBase
import kotlinx.coroutines.flow.Flow

class Repository(private val db: LivroDataBase) {
    suspend fun upsertLivro(livro: Livro) {
        db.livroDao().upsertLivro(livro)
    }

    suspend fun deleteLivro(livro: Livro) {
        db.livroDao().deleteLivro(livro)
    }

    fun getAllLivros(): Flow<List<Livro>> {
        return db.livroDao().getAllLivro()
    }
}

package com.example.ibm_app.viewModel

import androidx.lifecycle.ViewModel
import com.example.ibm_app.roomDB.Livro
import kotlinx.coroutines.flow.Flow

class LivroViewModel(private val repository: IRepository) : ViewModel() {
    val livros: Flow<List<Livro>> = repository.getAllLivros()

    suspend fun upsertLivro(livro: Livro) {
        repository.upsertLivro(livro)
    }

    suspend fun deleteLivro(livro: Livro) {
        repository.deleteLivro(livro)
    }
}

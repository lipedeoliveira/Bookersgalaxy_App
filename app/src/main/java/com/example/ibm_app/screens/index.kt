// Caminho do arquivo: ui/Index.kt
package com.example.ibm_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.ibm_app.ui.theme.IBM_APPTheme
import com.example.ibm_app.viewModel.LivroViewModel
import com.example.ibm_app.roomDB.LivroDataBase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.ibm_app.viewModel.Repository

fun index(activity: ComponentActivity) {
    // Banco de dados (pode ser inicializado aqui ou passado como dependência)
    val db = Room.databaseBuilder(
        activity.applicationContext,
        LivroDataBase::class.java,
        "livro.db"
    ).build()

    // Configurações da interface
    activity.enableEdgeToEdge()
    activity.setContent {
        IBM_APPTheme {
            val navController = rememberNavController()
            Surface {
                IBM_APP(navController, viewModel = livroViewModelFactory(db), mainActivity = activity)
            }
        }
    }
}

@Composable
fun livroViewModelFactory(db: LivroDataBase): LivroViewModel {
    return ViewModelProvider(
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LivroViewModel(Repository(db)) as T
            }
        }
    ).get(LivroViewModel::class.java)
}

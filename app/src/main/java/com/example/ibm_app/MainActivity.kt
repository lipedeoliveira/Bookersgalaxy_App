// Caminho do arquivo: MainActivity.kt
package com.example.ibm_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.ibm_app.screens.LoginScreen
import com.example.ibm_app.viewModel.LivroViewModel

class MainActivity : ComponentActivity() {
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var currentScreen by remember { mutableStateOf<Screen>(Screen.Login) } // Estado para controlar a tela

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val isLoginSuccessful = result.data?.getBooleanExtra("LOGIN_SUCCESSFUL", false) ?: false
                if (isLoginSuccessful) {
                    currentScreen = Screen.Home // Atualiza o estado para exibir a tela Home
                } else {
                    // Exiba uma mensagem de erro, se necessário
                }
            }
        }

        setContent {
            when (currentScreen) { // Exibe a tela com base no estado
                Screen.Login -> LoginScreen(launcher) { isLoginSuccessful ->
                    if (isLoginSuccessful) {
                        currentScreen = Screen.Home // Atualiza o estado para exibir a tela Home
                    } else {
                        // Exiba uma mensagem de erro, se necessário
                    }
                }
                Screen.Home -> {
                    val navController = rememberNavController()
                    val viewModel: LivroViewModel = viewModel()
                    IBM_APP(navController, viewModel)
                }
            }
        }
    }
}

// Crie um enum para representar as telas
enum class Screen {
    Login,
    Home
}
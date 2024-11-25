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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.ibm_app.screens.LoginScreen
import com.example.ibm_app.viewModel.LivroViewModel

class MainActivity : ComponentActivity() {
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val isLoginSuccessful = result.data?.getBooleanExtra("LOGIN_SUCCESSFUL", false) ?: false
                if (isLoginSuccessful) {
                    // Chame IBM_APP
                    setContent {
                        val navController = rememberNavController()
                        val viewModel: LivroViewModel = viewModel()
                        IBM_APP(navController, viewModel)
                    }
                }
            }
        }

        setContent {
            LoginScreen(launcher) { isLoginSuccessful ->
                // ... (código para lidar com o resultado do login)
            }
        }
    }
}
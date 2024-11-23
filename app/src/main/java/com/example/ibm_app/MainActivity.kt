// Caminho do arquivo: MainActivity.kt
package com.example.ibm_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.ibm_app.ui.index

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        index(this) // Chama o index
    }
}

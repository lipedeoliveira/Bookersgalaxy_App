package com.example.ibm_app.screens

import android.content.Intent
import androidx.activity.addCallback
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ibm_app.MainActivity
import com.example.ibm_app.R // Certifique-se de que o ícone do Google esteja nesta pasta `res/drawable`
import androidx.compose.ui.platform.LocalContext
import androidx.room.Index
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ibm_app.roomDB.Administrador
import com.example.ibm_app.roomDB.AdministradorDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(launcher: ActivityResultLauncher<Intent>) {
    // Contexto para navegação entre atividades
    var emailInserido by remember { mutableStateOf("") }
    var senhaInserida by remember { mutableStateOf("") }
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context.applicationContext,
        AdministradorDataBase::class.java,
        "administrador_database"
    )


        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Insira os dados do administrador aqui
                val administrador = Administrador(nome = "Admin", email = "admin@example.com", senha = "adm123") // Substitua pelos dados desejados
                val administradorDao = AdministradorDataBase.getInstance(context).administradorDao()
                // Insira o administrador em uma coroutine, pois é uma operação de suspensão
                GlobalScope.launch(Dispatchers.IO) {
                    administradorDao.inserirAdministrador(administrador)
                }
            }
        })
        .build()
    val administradorDao = db.administradorDao()
    val scope = rememberCoroutineScope()
    var showSnackbar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFA8C5A8), // Verde claro
                        Color(0xFFFFD6CC)  // Rosa claro
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "FAZER LOGIN COM:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Lógica para login com Google */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google), // Adicione o ícone na pasta drawable
                        contentDescription = "Google Icon",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Google",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            var emailInserido by remember { mutableStateOf("") }
            var senhaInserida by remember { mutableStateOf("") }
            TextField(
                value = emailInserido,
                onValueChange = {emailInserido = it},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(24.dp)) // Maior espaço entre os campos
            TextField(
                value = senhaInserida,
                onValueChange = { senhaInserida = it },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Esqueceu a senha?",
                fontSize = 14.sp,
                color = Color(0xFF757575), // Cinza
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp) // Alinhado à esquerda
                    .clickable { /* Lógica para recuperação de senha */ },
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(24.dp))

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Criar nova conta",
                fontSize = 14.sp,
                color = Color(0xFF757575), // Cinza
                modifier = Modifier.clickable { /* Lógica para criar conta */ },
                fontWeight = FontWeight.Bold
            )

        }
            Button(onClick = {
                scope.launch {
                    val administrador = administradorDao.getAdministradorPorEmail(emailInserido).firstOrNull()
                    if (administrador != null && administrador.senha == senhaInserida) {
                        // Redirecione para home.kt
                        val intent = Intent().putExtra("LOGIN_SUCCESSFUL", true)
                        launcher.launch(intent)
                    } else {
                        // Atualize o estado para exibir o Snackbar
                        showSnackbar = true
                    }
                }
            }) {
                // ...
            }

            // Observe o estado e exiba o Snackbar quando necessário
            LaunchedEffect(showSnackbar) {
                if (showSnackbar) {
                    snackbarHostState.showSnackbar(
                        message = "Email ou senha incorretos",
                        duration = SnackbarDuration.Short
                    )
                    // Redefina o estado após exibir o Snackbar
                    showSnackbar = false
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {}
    LoginScreen(launcher)


}

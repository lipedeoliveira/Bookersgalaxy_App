package com.example.ibm_app.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ibm_app.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.material.icons.Icons
import androidx.room.InvalidationTracker
import com.example.ibm_app.FakeRepository
import com.example.ibm_app.roomDB.Livro
import com.example.ibm_app.roomDB.LivroDao
import com.example.ibm_app.roomDB.LivroDataBase
import com.example.ibm_app.viewModel.LivroViewModel
import com.example.ibm_app.viewModel.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
/*
@Composable
fun AddBook(db:LivroDataBase,onSave:()->Unit) {

   val repository = Repository(db)
    val viewModel = LivroViewModel(repository)


    Divider()

    var nomeLivro by remember { mutableStateOf("") }
    var nomeAutor by remember { mutableStateOf("") }
    var nomeEditora by remember { mutableStateOf("") }
    var qntPaginas by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var sinopse by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imagePlaceholder by remember { mutableStateOf(R.drawable.ic_launcher_foreground) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF96B39D), // VERDE
                        Color.White,
                        Color(0xFFF3C3B2) // ROSA CLARO
                    )
                )
            ),

        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Title
        Text(
            text = "Cadastrar Livro",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Book Image
        Box(
            modifier = Modifier
                .size(150.dp)
                .clickable { launcher.launch("image/*") }
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {

        }

        // Book Name
        CustomTextField(value = nomeLivro, onValueChange = { nomeLivro = it }, label = "Nome do Livro")

        // Author Name
        CustomTextField(value = nomeAutor, onValueChange = { nomeAutor = it }, label = "Nome do Autor")

        // Publisher Name
        CustomTextField(value = nomeEditora, onValueChange = { nomeEditora = it }, label = "Nome da Editora")

        // Page Count
        CustomTextField(value = qntPaginas, onValueChange = { qntPaginas = it }, label = "Quantidade de Páginas")

        // Genre
        CustomTextField(value = genero, onValueChange = { genero = it }, label = "Gênero")

        // Synopsis
        CustomTextField(
            value = sinopse,
            onValueChange = { sinopse = it },
            label = "Sinopse",
            isSingleLine = false,
            maxLines = 20
        )

        // ISBN
        CustomTextField(value = isbn, onValueChange = { isbn = it }, label = "ISBN")

        // Price
        CustomTextField(value = preco, onValueChange = { preco = it }, label = "Preço")

        // Submit Button
        Button(
            onClick = {
                if (nomeLivro.isNotEmpty() && nomeAutor.isNotEmpty() && qntPaginas.isNotEmpty()) {
                    val livro = Livro(
                        titulo = nomeLivro,
                        autor = nomeAutor,
                        nomeEditora = nomeEditora,
                        qntPaginas = qntPaginas.toIntOrNull() ?: 0,
                        genero = genero,
                        sinopse = sinopse,
                        isbn = isbn,
                        preco = preco
                    )

                    // Chama o ViewModel para salvar o livro
                    viewModel.addLivro(livro)

                    // Limpa os campos após salvar
                    nomeLivro = ""
                    nomeAutor = ""
                    nomeEditora = ""
                    qntPaginas = ""
                    genero = ""
                    sinopse = ""
                    isbn = ""
                    preco = ""

                    // Executa a callback para notificar que o livro foi salvo
                    onSave()
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Salvar")
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isSingleLine: Boolean = true,
    maxLines: Int = 1
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.width(320.dp),
        label = { Text(text = label) },
        singleLine = isSingleLine,
        maxLines = maxLines,
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Gray
        )
    )
}
*/
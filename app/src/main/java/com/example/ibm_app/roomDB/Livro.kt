package com.example.ibm_app.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Livro(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo:String,
    val autor:String,
    val nomeEditora:String,
    var qntPaginas: Int,
    val genero:String,
    val sinopse:String,
    val isbn:String,
    val preco:String,
    val imageUri: String? = null // Para armazenar a URI da imagem, se necessário

)

package com.example.armazenarinf

data class Tarefa(
    val id: Int = 0,
    val descricao: String,
    val responsavel: String,
    val porcentagem: Int
)
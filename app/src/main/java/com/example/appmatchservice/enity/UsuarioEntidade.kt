package com.example.appmatchservice.enity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsuarioEntidade(

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "usuario_id")
    var id: Int = 0,

    @ColumnInfo(name = "usuario_nome")
    val nome: String,

    @ColumnInfo(name = "usuario_senha")
    val senha: String,

    @ColumnInfo(name = "usuario_telefone")
    val telefone: String,

    @ColumnInfo(name = "usuario_email")
    val email: String
)
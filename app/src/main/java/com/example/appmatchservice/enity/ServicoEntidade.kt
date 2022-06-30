package com.example.appmatchservice.enity

import android.widget.Spinner
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servico_table")
data class ServicoEntidade(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "servico_id")
    val id: Int = 0,

    @ColumnInfo(name = "servico_descricao")
    val descricao: String,

    @ColumnInfo(name = "servico_categoria")
    val categoria: String,

    @Embedded
    val endereco: EnderecoDTO,

    @Embedded
    val usuario: UsuarioEntidade
    )
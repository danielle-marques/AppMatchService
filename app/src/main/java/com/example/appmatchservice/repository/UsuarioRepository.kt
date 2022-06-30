package com.example.appmatchservice.repository

import android.content.Context
import com.example.appmatchservice.database.AppDatabase
import com.example.appmatchservice.enity.UsuarioEntidade

class UsuarioRepository(private val context: Context) {

    fun inserir(usuarioEntidade: UsuarioEntidade){
        AppDatabase.getInstance(context)?.getUsuarioDAO()?.inserir(usuarioEntidade)
    }

    fun buscarPorId(id: Int): UsuarioEntidade? {
        return AppDatabase.getInstance(context)?.getUsuarioDAO()?.buscarPorId(id)
    }

    fun buscar(): List<UsuarioEntidade>? {
        return AppDatabase.getInstance(context)?.getUsuarioDAO()?.buscar()
    }

    fun buscarPorEmail(email: String): UsuarioEntidade? {
        return AppDatabase.getInstance(context)?.getUsuarioDAO()?.buscarPorEmail(email)
    }

}
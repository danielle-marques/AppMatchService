package com.example.appmatchservice.repository

import android.content.Context
import com.example.appmatchservice.adapter.ServicoAdapter
import com.example.appmatchservice.database.AppDatabase
import com.example.appmatchservice.enity.ServicoEntidade
import com.example.appmatchservice.enity.UsuarioEntidade

class ServicoRepository(private val context: Context) {

    fun inserir(servicoEntidade: ServicoEntidade) {
        AppDatabase.getInstance(context)?.getServicoDAO()
            ?.inserir(servicoEntidade)
    }

    fun buscar(): List<ServicoEntidade>? {
        return AppDatabase.getInstance(context)?.getServicoDAO()?.buscar()
    }

    fun buscarPorCategoria(categoria: String) : List<ServicoEntidade>? {
        return  AppDatabase.getInstance(context)?.getServicoDAO()?.buscarPorCategoria(categoria)
    }
    fun buscarServicoPorID(id: Int) : List<ServicoEntidade>?{
        return AppDatabase.getInstance(context)?.getServicoDAO()?.buscarServicoPorID(id)

    }
    fun deletarServico(servicoEntidade: ServicoEntidade){
        AppDatabase.getInstance(context)?.getServicoDAO()?.delete(servicoEntidade)
    }


}
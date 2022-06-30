package com.example.appmatchservice.dao

import androidx.room.*
import com.example.appmatchservice.adapter.ServicoAdapter
import com.example.appmatchservice.enity.ServicoEntidade

@Dao
interface ServicoDAO {

    @Insert()
    fun inserir(servicoEntidade: ServicoEntidade)

    @Delete
    fun delete(servicoEntidade: ServicoEntidade)

    @Query("SELECT * FROM servico_table")
    fun buscar(): List<ServicoEntidade>

    @Query("SELECT * FROM servico_table WHERE servico_categoria = :categoria")
    fun buscarPorCategoria(categoria: String): List<ServicoEntidade>

    @Query("SELECT * FROM servico_table WHERE usuario_id = :id")
    fun buscarServicoPorID (id: Int): List<ServicoEntidade>

}
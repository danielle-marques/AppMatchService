package com.example.appmatchservice.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appmatchservice.enity.UsuarioEntidade

@Dao
interface UsuarioDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserir(usuarioEntidade: UsuarioEntidade)

    @Query("select * from usuarioentidade where usuario_id = :id")
    fun buscarPorId(id: Int): UsuarioEntidade

    @Query("select * from usuarioentidade")
    fun buscar() : List<UsuarioEntidade>

    @Query("select * from usuarioentidade where usuario_email = :email")
    fun buscarPorEmail(email: String) : UsuarioEntidade

}
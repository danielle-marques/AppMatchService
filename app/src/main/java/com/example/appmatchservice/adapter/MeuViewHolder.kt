package com.example.appmatchservice.adapter

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmatchservice.R
import com.example.appmatchservice.enity.ServicoEntidade

class MeuViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvCategoria: TextView = itemView.findViewById(R.id.tvCategoria)
    private val tvDescricao: TextView = itemView.findViewById(R.id.tvDescricao)
    private val tvRua: TextView = itemView.findViewById(R.id.tvRua)
    private val tvNumero: TextView = itemView.findViewById(R.id.tvNumero)
    private val tvCidade: TextView = itemView.findViewById(R.id.tvCidade)
    val ivDeletaMeuServico: ImageButton = itemView.findViewById(R.id.ivDeletaMeuServico)

    fun preencher(servicoEntidade: ServicoEntidade) {
        tvCategoria.text = servicoEntidade.categoria
        tvDescricao.text = servicoEntidade.descricao
        tvRua.text = servicoEntidade.endereco.rua
        tvNumero.text = servicoEntidade.endereco.numero
        tvCidade.text = servicoEntidade.endereco.cidade



    }
}
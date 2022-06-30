package com.example.appmatchservice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmatchservice.R
import com.example.appmatchservice.enity.ServicoEntidade

class MeuServicoAdapter (
    private val listaDeServico: MutableList<ServicoEntidade>,
    private val onClick: (ServicoEntidade) -> Unit
) :
    RecyclerView.Adapter<MeuViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeuViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_meus_servicos, parent, false)
        return MeuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeuViewHolder, position: Int) {
        val listaUmServico = listaDeServico[position]
        holder.preencher(listaUmServico)
        holder.ivDeletaMeuServico.setOnClickListener { onClick.invoke(listaUmServico)}
    }

    override fun getItemCount(): Int {
        return listaDeServico.size
    }

    fun refresh(novaLista: List<ServicoEntidade>) {
        listaDeServico.clear()
        listaDeServico.addAll(novaLista)
        notifyDataSetChanged()
    }


}
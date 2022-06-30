package com.example.appmatchservice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmatchservice.R
import com.example.appmatchservice.enity.ServicoEntidade

class ServicoAdapter(
    private val listaDeServico: MutableList<ServicoEntidade>
) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_lista_servico, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listaUmServico = listaDeServico[position]
        holder.preencher(listaUmServico)
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
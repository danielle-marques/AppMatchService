package com.example.appmatchservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmatchservice.adapter.MeuServicoAdapter
import com.example.appmatchservice.adapter.ServicoAdapter
import com.example.appmatchservice.enity.ServicoEntidade
import com.example.appmatchservice.repository.ServicoRepository
import com.example.appmatchservice.repository.UsuarioRepository

class MeuServicoActivity : AppCompatActivity() {

    lateinit var tvNome: TextView
    lateinit var tvTelefone: TextView
    lateinit var tvEmail: TextView
    lateinit var ivEditarMeuServico: ImageButton

    lateinit var ivVoltar: ImageButton
    lateinit var tvSemServico: TextView
    lateinit var rvMeuServico: RecyclerView

    private var usuarioId: Int? = null
    private var isEditMode = false

    private val usuarioRepository = UsuarioRepository(this)
    private val servicoRepository = ServicoRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meu_servico)

        initComponents()
        buscarDadosUsuario()
        buscarPorID()

        supportActionBar?.hide()

        usuarioId = intent.getIntExtra("usuario_id", 0)


        ivEditarMeuServico.setOnClickListener{
                val it = Intent(applicationContext, UsuarioActivity::class.java)
                it.putExtra("usuario_id", usuarioId)
                startActivity(it)


        }


        ivVoltar.setOnClickListener {
            finish()
        }



    }


    private fun startRecyvlerView(listaDeServicos: List<ServicoEntidade>) {
        rvMeuServico.adapter = MeuServicoAdapter(listaDeServicos.toMutableList(),
        onClick = {
            listaUmServico ->
            delete(listaUmServico)

        })
        rvMeuServico.layoutManager = LinearLayoutManager(this)

    }

    private fun delete(servico: ServicoEntidade) {
        val deletar = servicoRepository.deletarServico(servico)
        buscarPorID()
    }

    private fun initComponents() {
        tvNome = findViewById(R.id.tvNome)
        tvTelefone = findViewById(R.id.tvTelefone)
        tvEmail = findViewById(R.id.tvEmail)
        ivEditarMeuServico = findViewById(R.id.ivEditarMeuServico)
        ivVoltar = findViewById(R.id.ivVoltarMeuServico)
        rvMeuServico = findViewById(R.id.rvMeuServico)
        tvSemServico = findViewById(R.id.tvSemServico)
    }


    private fun buscarPorID() {
        val listaDeServicos = servicoRepository.buscarServicoPorID(usuarioId!!)


        listaDeServicos?.let {
            startRecyvlerView(listaDeServicos)

        }
        if (listaDeServicos != null) {
            if (listaDeServicos.isEmpty()) {
                tvSemServico.text = "Sem serviço cadastrado!"
                tvSemServico.visibility = View.VISIBLE
            }
        }
    }


    private fun buscarDadosUsuario() {
        usuarioId = intent.getIntExtra("usuario_id", 0)
        val userCadastrado = usuarioRepository.buscarPorId(usuarioId!!)

        userCadastrado?.let { userEntity ->
            tvNome.setText(userEntity.nome)
            tvEmail.setText(userEntity.email)
            tvTelefone.setText(userEntity.telefone)
        }

    }

    override fun onResume() {
        super.onResume()
        buscarDadosUsuario()
    }


}
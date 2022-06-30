package com.example.appmatchservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.appmatchservice.repository.UsuarioRepository

class HomeActivity : AppCompatActivity() {

    lateinit var tvNome: TextView
    lateinit var tvTelefone: TextView
    lateinit var tvEmail: TextView
    lateinit var tvCadastrarUmServico: TextView
    lateinit var tvBuscarUmServico: TextView
    lateinit var tvMeusServicos: TextView
    lateinit var ivVoltar: ImageButton

    var usuarioId = 0
    private val usuarioRepository = UsuarioRepository(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initComponents()
        buscarDadosUsuario()
        supportActionBar?.hide()

        usuarioId = intent.getIntExtra("usuario_id", 0)

        ivVoltar.setOnClickListener {
            Intent(this, MainActivity::class.java).let {
                startActivity(it)
                finish()
            }
        }
        tvCadastrarUmServico.setOnClickListener {
            val intent = Intent(this, CadastroServicoActivity::class.java)
            intent.putExtra("usuario_id", usuarioId)
            startActivity(intent)

        }
        tvBuscarUmServico.setOnClickListener {
            Intent(this, BuscarServicoActivity::class.java).let {
                startActivity(it)

            }
        }
        tvMeusServicos.setOnClickListener {
            val intent = Intent(this, MeuServicoActivity::class.java)
            intent.putExtra("usuario_id", usuarioId)
            startActivity(intent)


        }


    }

    private fun initComponents() {
        tvNome = findViewById(R.id.tvNome)
        tvTelefone = findViewById(R.id.tvTelefone)
        tvEmail = findViewById(R.id.tvEmail)
        tvCadastrarUmServico = findViewById(R.id.tvCadastrarUmServico)
        tvBuscarUmServico = findViewById(R.id.tvBuscarUmServico)
        tvMeusServicos = findViewById(R.id.tvMeusServicos)
        ivVoltar = findViewById(R.id.ivVoltarHome)
    }

    private fun buscarDadosUsuario() {
        usuarioId = intent.getIntExtra("usuario_id", 1)
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
package com.example.appmatchservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.appmatchservice.enity.UsuarioEntidade
import com.example.appmatchservice.repository.UsuarioRepository
import com.google.android.material.textfield.TextInputLayout

class UsuarioActivity : AppCompatActivity() {

    lateinit var tilEmail: TextInputLayout
    lateinit var tilNome: TextInputLayout
    lateinit var tilSenha: TextInputLayout
    lateinit var tilTelefone: TextInputLayout
    lateinit var bSave: Button
    lateinit var ivVoltar: ImageButton
    lateinit var tvTitulo: TextView

    private val usuarioRepository = UsuarioRepository(this)
    private var usuarioId: Int? = null
    private var isEditMode = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)
        initComponents()
        userCadastrado()

        supportActionBar?.hide()


        bSave.setOnClickListener { saveToLocalDb() }

        ivVoltar.setOnClickListener {
            Intent(this, MainActivity::class.java).let {
                startActivity(it)
                finish()
            }
        }

    }


    private fun proximaTela(clazz: Class<*>) {
        val it = Intent(this, clazz)
        it.putExtra("usuario_id", 0)
        startActivity(it)

    }

    private fun initComponents() {
        tilEmail = findViewById(R.id.tilEmail)
        tilNome = findViewById(R.id.tilNome)
        tilSenha = findViewById(R.id.tilSenha)
        tilTelefone = findViewById(R.id.tilTelefone)
        bSave = findViewById(R.id.bSave)
        ivVoltar = findViewById(R.id.ivVoltarCadastroUsuario)
        tvTitulo = findViewById(R.id.tvTitulo)
    }

    private fun saveToLocalDb() {

        val usuarioEntidade: UsuarioEntidade

        if (isEditMode) {

            usuarioEntidade = UsuarioEntidade(
                id = usuarioId!!,
                nome = tilNome.editText?.text.toString(),
                email = tilEmail.editText?.text.toString(),
                telefone = tilTelefone.editText?.text.toString(),
                senha = tilSenha.editText?.text.toString()
            )
        } else {
            usuarioEntidade =
                UsuarioEntidade(
                    nome = tilNome.editText?.text.toString(),
                    email = tilEmail.editText?.text.toString(),
                    senha = tilSenha.editText?.text.toString(),
                    telefone = tilTelefone.editText?.text.toString()
                )
        }
        usuarioRepository.inserir(usuarioEntidade)

        if (isEditMode) {
            finish()

        } else {
            proximaTela(MainActivity::class.java)
        }
        finish()


    }

    fun userCadastrado() {
        usuarioId = intent.getIntExtra("usuario_id", 0)
        isEditMode = if (usuarioId!! > 0) true else false

        if (isEditMode) {
            tvTitulo.text = "Alterar Cadastro"

            val userCadastrado = usuarioRepository.buscarPorId(usuarioId!!)
            userCadastrado?.let { user ->
                tilNome.editText?.setText(user.nome)
                tilSenha.editText?.setText(user.senha)
                tilTelefone.editText?.setText(user.telefone)
                tilEmail.editText?.setText(user.email)
            }
        }
    }
}
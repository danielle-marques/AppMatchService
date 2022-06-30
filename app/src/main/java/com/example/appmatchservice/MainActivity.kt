package com.example.appmatchservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.appmatchservice.enity.UsuarioEntidade
import com.example.appmatchservice.repository.ServicoRepository
import com.example.appmatchservice.repository.UsuarioRepository
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var tilEmail: TextInputLayout
    lateinit var tilSenha: TextInputLayout
    lateinit var bLogin: MaterialButton
    lateinit var tvCadastrar: TextView
    lateinit var tvBuscarUmServico: TextView

    val usuarioRepository = UsuarioRepository(this)
    var usuarioEntidade: UsuarioEntidade? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        criarClickListener()
        supportActionBar?.hide()




    }

    private fun initComponents() {
        tilEmail = findViewById(R.id.tilEmail)
        tilSenha = findViewById(R.id.tilSenha)
        bLogin = findViewById(R.id.bLogin)
        tvCadastrar = findViewById(R.id.tvCadastrar)
        tvBuscarUmServico = findViewById(R.id.tvBuscarUmServico)

    }
    private fun criarClickListener() {
        bLogin.setOnClickListener {validarUsuario()}
        tvCadastrar.setOnClickListener { proximaTela(UsuarioActivity::class.java) }
        tvBuscarUmServico.setOnClickListener { proximaTela(BuscarServicoActivity::class.java) }

    }

    private fun validarUsuario() {
        val email = tilEmail.editText?.text.toString()
        val senha = tilSenha.editText?.text.toString()
        val usuario = usuarioRepository.buscarPorEmail(email)

        if (email.isNotEmpty()){
            tilEmail.error = null
            if (email.equals(usuario?.email)){
                tilEmail.error = null
                if (senha.isNotEmpty()){
                    tilSenha.error = null
                    if (senha.equals(usuario?.senha)) {
                        val userCadastrado = usuario?.id
                        usuario?.let {
                            val it = Intent(this, HomeActivity::class.java)
                            it.putExtra("usuario_id", userCadastrado)
                            startActivity(it)

                        }
                    }else {
                        tilSenha.error = "Senha incorreta"
                    }
                } else {
                    tilSenha.error = "Campo de preenchimento obrigatório"
                }
            } else {
                tilEmail.error = "Email não cadastrado"
                tilSenha.error = "Campo de preenchimento obrigatório"
            }
        } else {
            tilEmail.error = "Campo de preenchimento obrigatório"
            tilSenha.error = "Campo de preenchimento obrigatório"
        }
    }


    private fun proximaTela(clazz: Class<*>){
        val it = Intent(this, clazz)
        startActivity(it)
        finish()
    }

    override fun onClick(p0: View?) {
        Intent(this, UsuarioActivity::class.java).let{
            startActivity(it)
        }
        Intent(this, BuscarServicoActivity::class.java).let {
            startActivity(it)

        }
    }


}
package com.example.appmatchservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isNotEmpty
import com.example.appmatchservice.enity.EnderecoDTO
import com.example.appmatchservice.enity.ServicoEntidade
import com.example.appmatchservice.enity.UsuarioEntidade
import com.example.appmatchservice.repository.ServicoRepository
import com.example.appmatchservice.repository.UsuarioRepository
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class CadastroServicoActivity : AppCompatActivity() {

    lateinit var tilRua: TextInputLayout
    lateinit var tilNumero: TextInputLayout
    lateinit var tilCidade: TextInputLayout
    lateinit var tilDescricao: TextInputLayout
    lateinit var bSave: MaterialButton
    lateinit var spnCadastroServico: Spinner
    lateinit var ivVoltar: ImageButton

    val servicoRepository = ServicoRepository(this)
    val usuarioRepository = UsuarioRepository(this)
    var usuarioEntidade: UsuarioEntidade? = null
    var userId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_servico)
        initComponents()

        supportActionBar?.hide()
        userId = intent.getIntExtra("usuario_id", 0)
        usuarioEntidade = usuarioRepository.buscarPorId(userId!!)
        bSave.setOnClickListener { saveToLocalDb() }

        ivVoltar.setOnClickListener {
            Intent(this, HomeActivity::class.java).let {
                startActivity(it)
                finish()
            }
        }

        val categorias =
            arrayOf("Administrativo", "Artesanato", "Evento", "Limpeza", "Manutenção")
        val arrayAdapter = ArrayAdapter<String>(this, R.layout.item_spinner, categorias)
        spnCadastroServico.adapter = arrayAdapter
        spnCadastroServico.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


    }

    private fun initComponents() {

        tilRua = findViewById(R.id.tilRua)
        tilNumero = findViewById(R.id.tilNumero)
        tilCidade = findViewById(R.id.tilCidade)
        bSave = findViewById(R.id.bSaveCadastro)
        spnCadastroServico = findViewById(R.id.spnCadastroServico)
        tilDescricao = findViewById(R.id.tilDescricao)
        ivVoltar = findViewById(R.id.ivVoltarCadastro)
    }

    private fun saveToLocalDb() {

        val descricaoTela = tilDescricao.editText?.text.toString()
        val categoriaDaTela = spnCadastroServico.selectedItem.toString()
        val ruaDaTela = tilRua.editText?.text.toString()
        val numeroDaTela = tilNumero.editText?.text.toString()
        val cidadeDaTela = tilCidade.editText?.text.toString()

        if (descricaoTela.isNotEmpty() && ruaDaTela.isNotEmpty() && numeroDaTela.isNotEmpty() && cidadeDaTela.isNotEmpty()) {
            tilDescricao.error = null
            tilRua.error = null
            tilNumero.error = null
            tilCidade.error = null

            usuarioEntidade?.let { userEntity ->
                ServicoEntidade(
                    descricao = descricaoTela,
                    categoria = categoriaDaTela,
                    endereco = EnderecoDTO(
                        rua = ruaDaTela,
                        numero = numeroDaTela,
                        cidade = cidadeDaTela
                    ),
                    usuario = userEntity

                ).also {
                    servicoRepository.inserir(it)

                }
            }
            finish()

        } else {

            tilDescricao.error = "Não pode estar em branco"
            tilCidade.error = "Não pode estar em branco"
            tilRua.error = "Não pode estar em branco"
            tilNumero.error = "Não pode estar em branco"

        }

    }




}
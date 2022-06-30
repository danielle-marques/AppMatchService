package com.example.appmatchservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmatchservice.adapter.ServicoAdapter
import com.example.appmatchservice.enity.ServicoEntidade
import com.example.appmatchservice.repository.ServicoRepository

class BuscarServicoActivity : AppCompatActivity() {

    lateinit var ivVoltar: ImageButton
    lateinit var spnCadastroServico: Spinner
    lateinit var ivPesquisar: ImageButton
    lateinit var rvBuscarServico: RecyclerView
    lateinit var tvSemServico: TextView

    private var usuarioID: Int? = null
    private var isEditMode = false


    val servicoRepository = ServicoRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_servico)

        initComponents()
        buscarTodosOsServicos()
        supportActionBar?.hide()


        usuarioID = intent.getIntExtra("usuario_id", 0)
        isEditMode = if (usuarioID!! > 0) true else false


        ivVoltar.setOnClickListener {
            if (isEditMode) {
                val it = Intent(applicationContext, HomeActivity::class.java)
                it.putExtra("usuario_id", usuarioID)
                startActivity(it)

            } else {
                val it = Intent(applicationContext, MainActivity::class.java)
                startActivity(it)
                finish()
            }
        }

        ivPesquisar.setOnClickListener {
            buscarServicosPorCategoria()
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
        ivVoltar = findViewById(R.id.ivVoltarBuscar)
        spnCadastroServico = findViewById(R.id.spnBuscarServico)
        ivPesquisar = findViewById(R.id.ivPesquisar)
        rvBuscarServico = findViewById(R.id.rvBuscarServico)
        tvSemServico = findViewById(R.id.tvSemServico)
    }

    private fun buscarTodosOsServicos() {
        val listaDeServicos = servicoRepository.buscar()
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


    private fun startRecyvlerView(listaDeServico: List<ServicoEntidade>) {
        rvBuscarServico.adapter = ServicoAdapter(listaDeServico.toMutableList())
        rvBuscarServico.layoutManager = LinearLayoutManager(this)
    }

    private fun buscarServicosPorCategoria() {
        val categoria = spnCadastroServico.selectedItem.toString()
        val listaServicoPorCategoria = servicoRepository.buscarPorCategoria(categoria)
        val adapter = rvBuscarServico.adapter as ServicoAdapter

        listaServicoPorCategoria?.let {
            adapter.refresh(it)
        }
        if (listaServicoPorCategoria != null) {
            if (listaServicoPorCategoria.isEmpty()) {
                tvSemServico.text = "Sem serviço cadastrado!"
                tvSemServico.visibility = View.VISIBLE
            }
        }

    }


}









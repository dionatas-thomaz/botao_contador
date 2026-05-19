package com.example.armazenarinf
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class InserirActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_inserir)

        val helper = TarefaHelper(this)

        val btnSalvar =
            findViewById<Button>(R.id.btnSalvar)

        btnSalvar.setOnClickListener {

            val tarefa = Tarefa(

                descricao =
                    findViewById<EditText>(R.id.edtDescricao)
                        .text.toString(),

                responsavel =
                    findViewById<EditText>(R.id.edtResponsavel)
                        .text.toString(),

                porcentagem =
                    findViewById<EditText>(R.id.edtPorcentagem)
                        .text.toString().toInt()
            )

            helper.inserirTarefa(tarefa)

            Toast.makeText(
                this,
                "Tarefa salva!",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }
    }
}
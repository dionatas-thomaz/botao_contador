package com.example.armazenarinf

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var txtContador: TextView
    private lateinit var btnClique: Button
    private var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtContador = findViewById(R.id.txtContador)
        btnClique = findViewById(R.id.btnClique)
        val prefs = getSharedPreferences("dados", Context.MODE_PRIVATE)
        contador = prefs.getInt("contador", 0)
        txtContador.text = contador.toString()

        btnClique.setOnClickListener {
            contador++
            txtContador.text = contador.toString()
            prefs.edit()
                .putInt("contador", contador)
                .apply()
        }
    }
}
package com.example.examen_prom

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Clase que hereda de AppCompatActivity
 */
class MainActivity : AppCompatActivity() {
    /**
     * Declaracion de variables
     */
    lateinit var boton : Button


    /**
     * Metodo que se ejecuta al crear la actividad
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        boton = findViewById(R.id.button)
        boton.setOnClickListener{onclickBoton()}
    }

    /**
     * Metodo que llama el listener.
     */
    fun onclickBoton(){
        val intent = Intent(this, TesororLista::class.java)
        startActivity(intent)
    }
}
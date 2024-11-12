package com.example.examen_prom

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class TesororLista(view : View) : AppCompatActivity(){
    lateinit var boton : Button
    lateinit var texto : EditText
    lateinit var recyclerTesoro : RecyclerView

    var tesoros = mutableListOf<String>()

    /**
     * Crea la vista de la aplicacion
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.lista)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        boton = findViewById(R.id.button)
        texto = findViewById(R.id.texto)
        recyclerTesoro = findViewById(R.id.recyclerTesoro)
    }
}
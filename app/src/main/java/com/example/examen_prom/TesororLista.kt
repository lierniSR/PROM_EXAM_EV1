package com.example.examen_prom

import android.app.Activity
import android.app.AlertDialog
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Clase que hereda de AppCompatActivity
 */
class TesororLista : AppCompatActivity(){
    /**
     * Declaracion de variables
     */
    lateinit var boton: Button
    lateinit var texto: EditText
    lateinit var recyclerTesoro: RecyclerView
    lateinit var adapter:AdapterTesoro
    lateinit var db : BBDD
    var tesoros = mutableListOf<Tesoro>()

    /**
     * Metodo que se ejecuta al crear la actividad
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista)

        boton = findViewById(R.id.button)
        boton.setOnClickListener{ aniadeTarea() }
        texto = findViewById(R.id.texto)
        recyclerTesoro = findViewById(R.id.recyclerTesoro)
        db = BBDD(this)

        tesoros = db.getTesoros().toMutableList()
        recyclerTesoro.layoutManager = LinearLayoutManager(this)
        adapter = AdapterTesoro(tesoros, {eliminarTarea(it)})
        recyclerTesoro.adapter = adapter
    }

    /**
     * Funcion que elimina la tarea
     *
     * @param posicion
     */
    private fun eliminarTarea(posicion:Int){
        val builder = AlertDialog.Builder(this) // 'this' se refiere al contexto actual

        val mediaPlayer = MediaPlayer.create(this, R.raw.eliminar)
        mediaPlayer.start()
        builder.setTitle("Advertencia")
            .setMessage("¿Esta seguro de que quiere eliminar el tesoro?")
            .setPositiveButton("Sí") { dialog, which ->
                db.deleteTarea(tesoros[posicion].nombre)
                tesoros.removeAt(posicion)
                adapter.notifyItemRemoved(posicion)
                val mediaPlayer2 = MediaPlayer.create(this, R.raw.eliminar2)
                mediaPlayer2.start()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss() // Cierra el diálogo
            }
        // Crear y mostrar el diálogo
        val dialog = builder.create()
        dialog.show()
    }

    /**
     * Metodo que llama el listener.
     * Lo añade a la lista para luego añadirlo a RecycledView
     * Para añadirlo se necesita un adapter y el viewHolder
     */
    private fun aniadeTarea() {
        if(texto.text.toString().isNotEmpty()){
            val tesoroAAniadir = texto.text.toString().split(",")
            val tesoro = Tesoro(tesoroAAniadir[0], tesoroAAniadir[1].toDouble(), tesoroAAniadir[2].toDouble())
            db.insertarTarea(tesoro)
            tesoros.add(tesoro)
            adapter.notifyItemInserted(adapter.itemCount - 1)
            texto.setText("")
        } else {
            val builder = AlertDialog.Builder(this) // 'this' se refiere al contexto actual

            builder.setTitle("Error")
                .setMessage("No se puede insertar un tesoro vacío")
                .setPositiveButton("Aceptar") { dialog, _ ->
                    // Acción al presionar "Aceptar"
                    dialog.dismiss() // Cierra el diálogo
                }
            // Crear y mostrar el diálogo
            val dialog = builder.create()
            dialog.show()
        }
    }
}
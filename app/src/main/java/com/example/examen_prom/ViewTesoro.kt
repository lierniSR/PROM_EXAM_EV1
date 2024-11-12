package com.example.examen_prom

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewTesoro(view : View): RecyclerView.ViewHolder(view)  {
    /**
     * Declaracion de variables
     */
    private val textViewTarea: TextView = view.findViewById(R.id.textViewTarea)
    private val imagenBorrar: ImageView = view.findViewById(R.id.imagenBorrar)

    /**
     * Se encarga de isnertar en el textview el texto que le llega como parametro
     *
     * @param tarea
     * @param onItemDone
     */
    fun insertarTarea(tarea:String, onItemDone:(Int) -> Unit){
        textViewTarea.text = tarea
        imagenBorrar.setOnClickListener{ onItemDone(adapterPosition) }
    }
}
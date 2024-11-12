package com.example.examen_prom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterTesoro (private val tesoros:List<Tesoro>, private val onItemDone: (Int) -> Unit):
    RecyclerView.Adapter<ViewTesoro>(){
        /**
         * Este metodo crea ViewHolder, y le asigne el item Task con ViewHolder
         *
         * @param parent
         * @param viewType
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTesoro {
            val layoutInflater = LayoutInflater.from(parent.context)
            return ViewTesoro(layoutInflater.inflate(R.layout.item_tesoro, parent, false))
        }

        /**
         * Este metodo es para hacer el scroll
         *
         * @param holder
         * @param position
         */
        override fun onBindViewHolder(holder: ViewTesoro, position: Int) {
            holder.insertarTarea(tesoros[position].toString(), onItemDone)
        }

        /**
         * Muestra al viewHolder cuantos items tiene que mostrar
         * Devuelve el tamaño del listado
         *
         * @return Int
         */
        override fun getItemCount(): Int {
            return tesoros.size
        }

    }
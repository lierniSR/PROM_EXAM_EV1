package com.example.examen_prom

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
* Creacion de la base de datos y sentencias SQL
*
*/
class BBDD (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    /**
     * Constantes de la base de datos
     */
    companion object{
        private const val DATABASE_NAME = "tesoros.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "tesoro"
        //Las columnas de la tabla
        private const val  COLUMN_NOMBRE = "nombre"
        private const val  COLUMN_LATITUD = "latitud"
        private const val  COLUMN_LONGITUD = "longitud"
    }

    /**
     * Creacion de la tabla
     */
    override fun onCreate(db: SQLiteDatabase?) {
        val createTablQuery = "CREATE TABLE $TABLE_NAME(" +
                "$COLUMN_NOMBRE TEXT PRIMARY KEY," +
                "$COLUMN_LATITUD DOUBLE," +
                "$COLUMN_LONGITUD DOUBLE)"
        db?.execSQL(createTablQuery)
    }

    /**
     * Actualizacion de la tabla
     */
    override fun onUpgrade(db: SQLiteDatabase?, viejaVerion: Int, nuevaVersion: Int) {
        val dropTablaQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTablaQuery)

        //Despues de eliminar creamos la tabla para no tener errores.
        onCreate(db)
    }

    /**
     * Insertar una tarea en la base de datos
     */
    fun insertarTarea(tesoro: Tesoro){
        //Abrimos en modo escritura
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, tesoro.nombre)
            put(COLUMN_LATITUD, tesoro.latitud)
            put(COLUMN_LONGITUD, tesoro.longitud)
        }

        //Insertamos datos
        db.insert(TABLE_NAME,null, values)
        //Cerramos BBDD
        db.close()
    }

    /**
     * Obtener todas las tareas de la base de datos
     */
    fun getTesoros() : List<Tesoro>{
        val listaTesoro = mutableListOf<Tesoro>()

        //Abrimos en modo lectura
        val db = readableDatabase

        val query = "SELECT * FROM $TABLE_NAME"

        //Lanzar un cursor
        val cursor = db.rawQuery(query, null)

        //Iterar y insertar las tareas a la lista
        while(cursor.moveToNext()){
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE))
            val latitud = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LATITUD))
            val longitud = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LONGITUD))

            //Creamo objeto tarea
            val tesoro = Tesoro(nombre, latitud, longitud)

            //Añadirmos a la lista
            listaTesoro.add(tesoro)
        }

        //Cerramos las conexiones
        cursor.close()
        db.close()


        return listaTesoro
    }

    /**
     * Eliminar una tarea dada su Nombre
     */
    fun deleteTarea(nombre : String){
        val db = writableDatabase

        val whereClauses = "$COLUMN_NOMBRE = ?"
        val whereArgs = arrayOf(nombre)

        //Eliminar objeto
        db.delete(TABLE_NAME, whereClauses, whereArgs)

        db.close()
    }
}
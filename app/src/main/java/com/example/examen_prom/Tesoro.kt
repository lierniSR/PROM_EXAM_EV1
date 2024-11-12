package com.example.examen_prom

/**
 * Clase tesoro con sus atributos
 */
class Tesoro(val nombre:String, val latitud:Double, val longitud:Double) {
    /**
     * Metodo toString para mostrar el nombre del tesoro
     */
    override fun toString(): String {
        return "$nombre ($latitud, $longitud)"
    }
}
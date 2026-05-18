package com.example.ra3trailerspeliculas.modelo

data class Pelicula(
    val id: Int,
    val nombre: String,
    val imagen: Int,
    val anio: Int,
    val pais: String,
    val duracionMinutos: Int,
    val generos: String,
    val fechaEstrenoEspana: String,
    val fechaEstrenoUSA: String,
    val direccion: String,
    val reparto: String,
    val sinopsis: String,
    val videoUrl: String
)
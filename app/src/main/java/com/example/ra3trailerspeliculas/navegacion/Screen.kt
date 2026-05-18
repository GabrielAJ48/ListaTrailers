package com.example.ra3trailerspeliculas.navegacion

sealed class Screen(val route: String) {

    object ListaPeliculas: Screen("listaPeliculas")

    object DetallePelicula: Screen("detallePelicula/{peliculaId}"){
        fun createRoute(peliculaId: Int) = "detallePelicula/$peliculaId"
    }

    object ReproducirPelicula: Screen("reproducirPelicula/{peliculaId}"){
        fun createRoute(peliculaId: Int) = "reproducirPelicula/$peliculaId"
    }
}
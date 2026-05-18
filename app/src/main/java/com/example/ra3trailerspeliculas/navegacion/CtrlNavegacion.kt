package com.example.ra3trailerspeliculas.navegacion

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ra3trailerspeliculas.vistas.DetallePelicula
import com.example.ra3trailerspeliculas.vistas.ListaPeliculasScreen
import com.example.ra3trailerspeliculas.viewmodel.PeliculasViewModel
import com.example.ra3trailerspeliculas.vistas.ReproductorVideo

@Composable
fun NavigationController(viewModel: PeliculasViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.ListaPeliculas.route) {
        composable(route = Screen.ListaPeliculas.route) {
            ListaPeliculasScreen(viewModel) { peliculaId ->
                navController.navigate(route = Screen.DetallePelicula.createRoute(peliculaId))
            }
        }

        composable(
            route = Screen.DetallePelicula.route,
            arguments = listOf(
                navArgument("peliculaId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val peliculaId = backStackEntry.arguments?.getInt("peliculaId") ?: 0
            val pelicula = viewModel.obtenerPeliculaPorId(peliculaId)

            if (pelicula != null) {
                DetallePelicula(
                    pelicula = pelicula,
                    onBack = { navController.popBackStack() },
                    onVerTrailer = { id -> navController.navigate(route = Screen.ReproducirPelicula.createRoute(peliculaId)) }
                )
            }
        }

        composable(
            route = Screen.ReproducirPelicula.route,
            arguments = listOf(navArgument("peliculaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val peliculaId = backStackEntry.arguments?.getInt("peliculaId") ?: 0
            val pelicula = viewModel.obtenerPeliculaPorId(peliculaId)

            if (pelicula != null) {
                ReproductorVideo(pelicula = pelicula, onBack = { navController.popBackStack() })
            }
        }
    }
}
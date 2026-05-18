package com.example.ra3trailerspeliculas.vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ra3trailerspeliculas.R
import com.example.ra3trailerspeliculas.fuentes.Fuentes
import com.example.ra3trailerspeliculas.viewmodel.PeliculasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaPeliculasScreen(viewModel: PeliculasViewModel, onPeliculaClick: (Int) -> Unit) {

    val peliculas by viewModel.peliculas.collectAsState()

    val fondoPrincipal = Color(0xFF141414)
    val textoClaro = Color(0xFFFFFFFF)

    Scaffold(
        containerColor = fondoPrincipal,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Estrenos de Cine",
                        fontWeight = FontWeight.Bold,
                        fontFamily = Fuentes.Montserrat
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = fondoPrincipal,
                    titleContentColor = textoClaro
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(peliculas) { pelicula ->
                TarjetaPelicula(
                    pelicula = pelicula,
                    onClick = { onPeliculaClick(pelicula.id) }
                )
            }
        }
    }
}
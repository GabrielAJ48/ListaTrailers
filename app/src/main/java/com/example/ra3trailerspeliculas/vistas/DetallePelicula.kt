package com.example.ra3trailerspeliculas.vistas

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ra3trailerspeliculas.modelo.Pelicula
import com.example.ra3trailerspeliculas.fuentes.Fuentes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallePelicula(pelicula: Pelicula, onBack: () -> Unit, onVerTrailer: (Int) -> Unit) {
    var sinopsisExpandida by remember { mutableStateOf(false) }

    val fondoPrincipal = Color(0xFF141414)
    val fondoTarjeta = Color(0xFF2A2A2A)
    val verdeAcento = Color(0xFF00E676)
    val textoClaro = Color(0xFFFFFFFF)
    val textoSecundario = Color(0xFFB0B0B0)

    Scaffold(
        containerColor = fondoPrincipal,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = pelicula.nombre,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Fuentes.Montserrat
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = fondoPrincipal,
                    titleContentColor = textoClaro,
                    navigationIconContentColor = textoClaro
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Ficha Técnica",
                fontWeight = FontWeight.Bold,
                color = verdeAcento,
                fontFamily = Fuentes.Montserrat
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                colors = CardDefaults.cardColors(containerColor = fondoTarjeta)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Director: ${pelicula.direccion}", color = textoSecundario, fontFamily = Fuentes.Montserrat)
                    Text("Reparto: ${pelicula.reparto}", color = textoSecundario, fontFamily = Fuentes.Montserrat)
                    Text("País: ${pelicula.pais}", color = textoSecundario, fontFamily = Fuentes.Montserrat)
                    Text("Duración: ${pelicula.duracionMinutos} min", color = textoSecundario, fontFamily = Fuentes.Montserrat)
                    Text("Estreno España: ${pelicula.fechaEstrenoEspana}", color = textoSecundario, fontFamily = Fuentes.Montserrat)
                    Text("Estreno USA: ${pelicula.fechaEstrenoUSA}", color = textoSecundario, fontFamily = Fuentes.Montserrat)
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Sinopsis", fontWeight = FontWeight.Bold, color = verdeAcento, fontFamily = Fuentes.Montserrat)
                Text("(Toca para leer más)", color = textoSecundario, fontFamily = Fuentes.Montserrat)
            }

            Text(
                text = if (sinopsisExpandida) pelicula.sinopsis else "${pelicula.sinopsis.take(80)}...",
                color = textoClaro,
                fontFamily = Fuentes.Montserrat,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(fondoTarjeta)
                    .clickable { sinopsisExpandida = !sinopsisExpandida }
                    .padding(12.dp)
                    .animateContentSize()
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onVerTrailer(pelicula.id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = verdeAcento,
                    contentColor = fondoPrincipal
                )
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = "Icono de reproducir")
                Spacer(modifier = Modifier.width(8.dp))
                Text("VER TRÁILER", fontWeight = FontWeight.Bold, fontFamily = Fuentes.Montserrat)
            }
        }
    }
}
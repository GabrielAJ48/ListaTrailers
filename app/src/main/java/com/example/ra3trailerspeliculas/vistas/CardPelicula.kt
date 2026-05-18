package com.example.ra3trailerspeliculas.vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ra3trailerspeliculas.modelo.Pelicula
import com.example.ra3trailerspeliculas.fuentes.Fuentes

@Composable
fun TarjetaPelicula(pelicula: Pelicula, onClick: () -> Unit) {
    val fondoTarjeta = Color(0xFF222222)
    val textoClaro = Color(0xFFFFFFFF)
    val textoSecundario = Color(0xFFB0B0B0)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = fondoTarjeta)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(pelicula.imagen),
                contentDescription = "Póster de ${pelicula.nombre}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = pelicula.nombre,
                    fontWeight = FontWeight.Bold,
                    color = textoClaro,
                    fontFamily = Fuentes.Montserrat
                )
                Text(
                    text = "Año: ${pelicula.anio} | ${pelicula.generos}",
                    color = textoSecundario,
                    fontFamily = Fuentes.Montserrat
                )
            }
        }
    }
}
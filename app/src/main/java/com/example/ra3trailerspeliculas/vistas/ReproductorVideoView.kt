package com.example.ra3trailerspeliculas.vistas

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ra3trailerspeliculas.fuentes.Fuentes
import com.example.ra3trailerspeliculas.modelo.Pelicula

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReproductorVideoView(pelicula: Pelicula, onBack: () -> Unit) {
    var isLoading by remember { mutableStateOf(true) }

    val verdeAcento = Color(0xFF00E676)
    val fondoPrincipal = Color(0xFF000000)
    val textoClaro = Color(0xFFFFFFFF)

    Scaffold(
        containerColor = fondoPrincipal,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tráiler: ${pelicula.nombre}",
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
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(fondoPrincipal),
            contentAlignment = Alignment.Center
        ) {
            AndroidView(
                factory = { context ->
                    VideoView(context).apply {
                        setVideoURI(Uri.parse(pelicula.videoUrl))

                        val mediaController = MediaController(context)
                        mediaController.setAnchorView(this)
                        setMediaController(mediaController)

                        setOnPreparedListener {
                            isLoading = false
                            start()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )

            if (isLoading) {
                CircularProgressIndicator(
                    color = verdeAcento,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}
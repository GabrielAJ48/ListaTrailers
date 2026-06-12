package com.example.ra3trailerspeliculas.vistas

import android.net.Uri
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.ra3trailerspeliculas.fuentes.Fuentes
import com.example.ra3trailerspeliculas.modelo.Pelicula
import com.example.ra3trailerspeliculas.viewmodel.SoundPoolViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReproductorVideo(
    pelicula: Pelicula,
    soundViewModel: SoundPoolViewModel,
    onBack: () -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current

    val verdeAcento = Color(0xFF00E676)
    val fondoPrincipal = Color(0xFF000000)
    val textoClaro = Color(0xFFFFFFFF)

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(Uri.parse(pelicula.videoUrl))
            setMediaItem(mediaItem)

            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (playbackState == Player.STATE_READY) {
                        isLoading = false
                    }
                }
            })

            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

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
                    IconButton(
                        onClick = {
                            soundViewModel.playSound(SoundPoolViewModel.Sonido.CLICK)
                            onBack()
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = textoClaro)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = fondoPrincipal,
                    titleContentColor = textoClaro
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
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        player = exoPlayer
                        useController = true
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

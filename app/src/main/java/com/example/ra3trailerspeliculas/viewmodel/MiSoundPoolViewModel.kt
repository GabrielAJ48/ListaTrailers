package com.example.ra3trailerspeliculas.viewmodel

import android.app.Application
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.lifecycle.AndroidViewModel
import com.example.ra3trailerspeliculas.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SoundPoolViewModel(application: Application) : AndroidViewModel(application) {

    private val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(3)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        )
        .build()

    enum class Sonido {
        CLICK,
        EXPANDIR,
        PLAY
    }

    private val soundIds = mutableMapOf<Sonido, Int>()
    private val loadedSoundIds = mutableSetOf<Int>()

    private val _isLoaded = MutableStateFlow(false)
    val isLoaded: StateFlow<Boolean> = _isLoaded

    init {
        soundPool.setOnLoadCompleteListener { _, sampleId, status ->
            if (status == 0) {
                loadedSoundIds.add(sampleId)
                _isLoaded.value = loadedSoundIds.size == soundIds.size
            }
        }
    }

    fun loadSound(sonido: Sonido, soundResId: Int) {
        val id = soundPool.load(getApplication(), soundResId, 1)
        soundIds[sonido] = id
        _isLoaded.value = false
    }

    fun loadSounds() {
        if (soundIds.isNotEmpty()) return

        loadSound(Sonido.CLICK, R.raw.click)
        loadSound(Sonido.EXPANDIR, R.raw.expandir)
        loadSound(Sonido.PLAY, R.raw.play)
    }

    fun playSound(sonido: Sonido) {
        soundIds[sonido]?.let { id ->
            if (loadedSoundIds.contains(id)) {
                soundPool.play(id, 1f, 1f, 1, 0, 1f)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        soundPool.release()
    }
}

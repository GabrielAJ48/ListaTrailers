package com.example.ra3trailerspeliculas.viewmodel


import androidx.lifecycle.ViewModel
import com.example.ra3trailerspeliculas.R
import com.example.ra3trailerspeliculas.modelo.Pelicula
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PeliculasViewModel : ViewModel() {

    private val listaPeliculas = listOf(
        Pelicula(
            id = 1,
            nombre = "Deadpool & Wolverine",
            imagen = R.drawable.deadpool_wolverine,
            anio = 2024,
            pais = "USA",
            duracionMinutos = 127,
            generos = "Acción, Comedia",
            fechaEstrenoEspana = "25/07/2024",
            fechaEstrenoUSA = "26/07/2024",
            direccion = "Shawn Levy",
            reparto = "Ryan Reynolds, Hugh Jackman, Emma Corrin",
            sinopsis = "Wade Wilson deja atrás su pasado como Deadpool y vive una vida tranquila. Pero cuando una amenaza externa pone en peligro todo su universo y a sus seres queridos, debe volver a ponerse el traje y convencer a un reacio y amargado Wolverine de que lo ayude en la misión más alocada de su vida.",
            videoUrl = "https://www.dropbox.com/scl/fi/e0vl6exw3dsm1j3a916hn/Deadpool-Y-Lobezno-de-Marvel-Studios-Tr-iler-Oficial-en-castellano-HD-tTM5weeCFvQ-.f398.mp4?rlkey=4r5ofvcj7g68g7nh54ktnfu8i&st=hwp0d1u4&raw=1"
        ),
        Pelicula(
            id = 2,
            nombre = "Dune: Parte Dos",
            imagen = R.drawable.dune,
            anio = 2024,
            pais = "USA",
            duracionMinutos = 166,
            generos = "Ciencia Ficción, Épica",
            fechaEstrenoEspana = "01/03/2024",
            fechaEstrenoUSA = "01/03/2024",
            direccion = "Denis Villeneuve",
            reparto = "Timothée Chalamet, Zendaya, Rebecca Ferguson",
            sinopsis = "El viaje mítico de Paul Atreides continúa mientras se une a Chani y a los Fremen en un camino de venganza contra los conspiradores que destruyeron a su familia. Enfrentándose a una elección entre el amor de su vida y el destino del universo conocido, se esfuerza por evitar un futuro terrible que solo él puede prever.",
            videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
        ),
        Pelicula(
            id = 3,
            nombre = "Del Revés 2 (Inside Out 2)",
            imagen = R.drawable.inside_out2,
            anio = 2024,
            pais = "USA",
            duracionMinutos = 96,
            generos = "Animación, Familiar",
            fechaEstrenoEspana = "19/06/2024",
            fechaEstrenoUSA = "14/06/2024",
            direccion = "Kelsey Mann",
            reparto = "Amy Poehler, Maya Hawke, Kensington Tallman",
            sinopsis = "Vuelve a la mente de una Riley recién llegada a la adolescencia. La sede central de sus emociones está sufriendo una repentina demolición para hacer sitio a algo totalmente inesperado: ¡nuevas emociones! Alegría, Tristeza, Ira, Miedo y Asco no saben cómo reaccionar cuando aparece Ansiedad.",
            videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
        ),
        Pelicula(
            id = 4,
            nombre = "Avatar: Fire and Ash",
            imagen = R.drawable.avatar,
            anio = 2025,
            pais = "USA",
            duracionMinutos = 180,
            generos = "Aventura, Fantasía",
            fechaEstrenoEspana = "19/12/2025",
            fechaEstrenoUSA = "19/12/2025",
            direccion = "James Cameron",
            reparto = "Sam Worthington, Zoe Saldaña, Sigourney Weaver",
            sinopsis = "Jake Sully y Neytiri se enfrentan a un nuevo y peligroso clan Na'vi: el Pueblo de las Cenizas, una tribu agresiva que habita en las zonas volcánicas de Pandora. La batalla por la supervivencia alcanza niveles nunca antes vistos mientras la familia explora territorios inexplorados y sumamente hostiles.",
            videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
        ),
        Pelicula(
            id = 5,
            nombre = "The Batman: Parte II",
            imagen = R.drawable.batman,
            anio = 2026,
            pais = "USA",
            duracionMinutos = 150,
            generos = "Acción, Crimen",
            fechaEstrenoEspana = "02/10/2026",
            fechaEstrenoUSA = "02/10/2026",
            direccion = "Matt Reeves",
            reparto = "Robert Pattinson, Zoë Kravitz, Colin Farrell",
            sinopsis = "Tras los devastadores eventos de la primera entrega, Bruce Wayne se adentra aún más en las oscuras y peligrosas sombras de Gotham City. Deberá enfrentarse a nuevas y retorcidas amenazas criminales, al mismo tiempo que descubre oscuros secretos familiares que cambiarán la historia de la ciudad.",
            videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
        )
    )

    private val _peliculas = MutableStateFlow(listaPeliculas)
    val peliculas: StateFlow<List<Pelicula>> = _peliculas.asStateFlow()
    fun obtenerPeliculaPorId(id: Int): Pelicula? {
        return listaPeliculas.find { it.id == id }
    }
}
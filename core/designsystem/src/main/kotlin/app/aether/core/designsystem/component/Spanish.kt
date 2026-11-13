package app.aether.core.designsystem.component

/** Common interface copy, translated without changing catalogue-provided song and artist titles. */
fun spanish(text: String): String = SpanishCopy[text] ?: text

private val SpanishCopy = mapOf(
"Listen" to "Escuchar", "Discover" to "Descubrir", "Library" to "Biblioteca", "Profile" to "Perfil",
"Settings" to "Ajustes", "How AETHER sounds, how it moves, and what it keeps." to "Cómo suena Astral Music, cómo se mueve y qué guarda.",
"Account" to "Cuenta", "Sources" to "Fuentes", "Your own sources" to "Tus propias fuentes",
"Music on this phone" to "Música en este teléfono", "Music folders" to "Carpetas de música",
"Lyrics files" to "Archivos de letras", "Sound" to "Sonido", "Data saver" to "Ahorro de datos",
"Crossfade" to "Fundido entre canciones", "Equaliser" to "Ecualizador", "Open" to "Abrir",
"Feel" to "Experiencia", "Apariencia" to "Apariencia", "Haptics" to "Vibración",
"Reduce motion" to "Reducir movimiento", "Full screen" to "Pantalla completa", "Glass" to "Cristal",
"Simple" to "Simple", "Liquid" to "Líquido", "Now Playing" to "Reproduciendo",
"Timed lyrics" to "Letras sincronizadas", "Libraries" to "Bibliotecas", "Off" to "Desactivado", "On" to "Activado",
"How lyrics are drawn" to "Estilo de las letras", "Verse" to "Verso", "Constellations" to "Constelaciones",
"Line" to "Línea", "Words in Latin letters" to "Letras en alfabeto latino", "Song video" to "Vídeo musical",
"The cover" to "La portada", "Original" to "Original", "Mono" to "Monocromo", "Halftone" to "Semitono",
"ASCII" to "ASCII", "Dither" to "Tramado", "Artists and albums" to "Artistas y álbumes",
"Artist and album art" to "Arte de artistas y álbumes", "Your data" to "Tus datos",
"Listening history" to "Historial de reproducción", "Recent searches" to "Búsquedas recientes",
"Software update" to "Actualización", "Look for updates" to "Buscar actualizaciones",
"Check now" to "Comprobar ahora", "Privacy" to "Privacidad", "About" to "Acerca de",
"Sign in" to "Iniciar sesión", "Sign out" to "Cerrar sesión", "Choose" to "Elegir",
"Add" to "Añadir", "Remove" to "Quitar", "Change" to "Cambiar", "Clear" to "Borrar",
"Share" to "Compartir", "Try again" to "Reintentar", "Add a source" to "Añadir una fuente",
"Asking…" to "Conectando…", "Recently played" to "Reproducido recientemente",
"From your listening" to "De tu música", "Your playlists" to "Tus listas", "Kept by you" to "Guardado por ti",
"On this phone" to "En este teléfono", "Liked songs" to "Canciones que te gustan", "Playlists" to "Listas",
"Kept" to "Guardado", "Songs" to "Canciones", "Albums" to "Álbumes", "Artists" to "Artistas",
"History" to "Historial", "Search" to "Buscar", "Search music" to "Buscar música",
"Pause" to "Pausar", "Play" to "Reproducir", "Next" to "Siguiente", "Back" to "Atrás",
"Cancel" to "Cancelar", "Done" to "Listo", "Wallpaper" to "Fondo de pantalla",
"Choose a photo for the home screen. The Liquid Glass stays above it." to "Elige una imagen para la pantalla principal. El cristal líquido seguirá por encima.",
"Choose image" to "Elegir imagen", "Change image" to "Cambiar imagen", "Remove wallpaper" to "Quitar fondo",
"Appearance" to "Apariencia"
)

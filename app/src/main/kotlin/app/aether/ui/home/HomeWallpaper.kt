package app.aether.ui.home

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import android.os.Build
import coil3.compose.AsyncImage

/** A user-selected home photograph, persisted with a read-only document permission. */
object HomeWallpaper {
    var uri: Uri? by mutableStateOf(null)
        private set
    private var loaded = false

    fun load(context: Context) {
        if (loaded) return
        loaded = true
        val saved = context.applicationContext.getSharedPreferences("astral_appearance", Context.MODE_PRIVATE)
            .getString("home_wallpaper", null)
        uri = saved?.let(Uri::parse)
    }

    fun set(context: Context, selected: Uri) {
        runCatching {
            context.contentResolver.takePersistableUriPermission(selected, android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.applicationContext.getSharedPreferences("astral_appearance", Context.MODE_PRIVATE)
            .edit().putString("home_wallpaper", selected.toString()).apply()
        uri = selected
        loaded = true
    }

    fun clear(context: Context) {
        context.applicationContext.getSharedPreferences("astral_appearance", Context.MODE_PRIVATE)
            .edit().remove("home_wallpaper").apply()
        uri = null
        loaded = true
    }
}


/**
 * The app-wide photograph. Every page shares it beneath its own content; Settings opts out.
 * A dark veil keeps type legible and lets the existing glass surfaces remain the foreground.
 */
@Composable
fun AppWallpaper(excluded: Boolean = false) {
    val context = LocalContext.current
    LaunchedEffect(Unit) { HomeWallpaper.load(context) }
    val selected = HomeWallpaper.uri
    Box(Modifier.fillMaxSize()) {
        if (!excluded && selected != null) {
            AsyncImage(
                model = selected,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().graphicsLayer {
                    renderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        BlurEffect(22f, 22f, TileMode.Clamp)
                    } else null
                },
            )
            Box(Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.58f)))
        } else {
            Box(Modifier.fillMaxSize().background(Color.Black))
        }
    }
}

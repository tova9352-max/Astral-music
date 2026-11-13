package app.aether.ui.home

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

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

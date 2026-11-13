package app.aether.core.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.aether.core.designsystem.theme.Ink
import app.aether.core.designsystem.theme.Type

/**
 * The cross of light from the CANTICUM mark: a long vertical beam, a shorter horizontal one, a
 * bright core in a soft halo, and faint diagonal glints. Additive, so it only ever adds light.
 */
@Composable
fun AstralEmblem(modifier: Modifier = Modifier, size: Dp = 120.dp, intensity: Float = 1f) {
    Canvas(modifier.size(size)) { drawAstralEmblem(intensity) }
}

/** Astral Studio orbital-star symbol in reflective silver with a restrained violet glint. */
fun DrawScope.drawAstralEmblem(intensity: Float = 1f) {
    val w = size.width
    val h = size.height
    val i = intensity.coerceIn(0f, 1.5f)
    val orbit = Path().apply {
        moveTo(w * 0.06f, h * 0.69f)
        cubicTo(w * 0.20f, h * 0.43f, w * 0.63f, h * 0.12f, w * 0.88f, h * 0.17f)
        cubicTo(w * 1.02f, h * 0.20f, w * 1.00f, h * 0.31f, w * 0.84f, h * 0.43f)
        cubicTo(w * 0.66f, h * 0.57f, w * 0.36f, h * 0.78f, w * 0.13f, h * 0.82f)
        cubicTo(w * 0.34f, h * 0.67f, w * 0.62f, h * 0.49f, w * 0.79f, h * 0.34f)
        cubicTo(w * 0.91f, h * 0.24f, w * 0.88f, h * 0.23f, w * 0.78f, h * 0.26f)
        cubicTo(w * 0.55f, h * 0.31f, w * 0.24f, h * 0.50f, w * 0.06f, h * 0.69f)
        close()
    }
    val star = Path().apply {
        moveTo(w * 0.64f, h * 0.17f)
        cubicTo(w * 0.655f, h * 0.40f, w * 0.69f, h * 0.46f, w * 0.89f, h * 0.52f)
        cubicTo(w * 0.69f, h * 0.58f, w * 0.655f, h * 0.64f, w * 0.64f, h * 0.88f)
        cubicTo(w * 0.625f, h * 0.64f, w * 0.59f, h * 0.58f, w * 0.39f, h * 0.52f)
        cubicTo(w * 0.59f, h * 0.46f, w * 0.625f, h * 0.40f, w * 0.64f, h * 0.17f)
        close()
    }
    drawCircle(
        Brush.radialGradient(listOf(Color(0x667E65C8), Color.Transparent), Offset(w * 0.64f, h * 0.52f), w * 0.31f),
        radius = w * 0.31f, center = Offset(w * 0.64f, h * 0.52f), alpha = 0.32f * i,
    )
    val silver = Brush.linearGradient(
        listOf(Color(0xFF596273), Color(0xFFF8FAFF), Color(0xFFB6A6E8), Color(0xFFE8ECF5), Color(0xFF777F91)),
        Offset(w * 0.05f, h * 0.82f), Offset(w * 0.93f, h * 0.17f),
    )
    drawPath(orbit, silver, alpha = i)
    drawPath(star, silver, alpha = i)
}
fun DrawScope.drawLightCross(intensity: Float = 1f, centre: Offset = center, extent: Float = size.minDimension) {
    val i = intensity.coerceIn(0f, 1.5f)
    val s = extent
    val white = Color.White
    // Halo
    drawCircle(
        Brush.radialGradient(listOf(white.copy(alpha = 0.42f * i), white.copy(alpha = 0.08f * i), Color.Transparent), centre, s * 0.3f),
        radius = s * 0.3f, center = centre, blendMode = BlendMode.Plus,
    )
    fun beam(halfLength: Float, thickness: Float, alpha: Float, vertical: Boolean) {
        val brush = if (vertical) {
            Brush.verticalGradient(listOf(Color.Transparent, white.copy(alpha = alpha), Color.Transparent), centre.y - halfLength, centre.y + halfLength)
        } else {
            Brush.horizontalGradient(listOf(Color.Transparent, white.copy(alpha = alpha), Color.Transparent), centre.x - halfLength, centre.x + halfLength)
        }
        val topLeft = if (vertical) Offset(centre.x - thickness / 2, centre.y - halfLength) else Offset(centre.x - halfLength, centre.y - thickness / 2)
        val sz = if (vertical) Size(thickness, halfLength * 2) else Size(halfLength * 2, thickness)
        drawRect(brush, topLeft, sz, blendMode = BlendMode.Plus)
    }
    // Long vertical, shorter horizontal: a cross, not a plus.
    beam(s * 0.5f, s * 0.012f, 0.95f * i, vertical = true)
    beam(s * 0.5f, s * 0.05f, 0.18f * i, vertical = true)
    beam(s * 0.3f, s * 0.011f, 0.9f * i, vertical = false)
    beam(s * 0.3f, s * 0.045f, 0.15f * i, vertical = false)
    // Faint diagonal glints.
    rotate(45f, centre) {
        beam(s * 0.11f, s * 0.008f, 0.5f * i, vertical = true)
        beam(s * 0.11f, s * 0.008f, 0.5f * i, vertical = false)
    }
    // The core.
    drawCircle(Brush.radialGradient(listOf(white.copy(alpha = i), white.copy(alpha = 0f)), centre, s * 0.04f), radius = s * 0.04f, center = centre, blendMode = BlendMode.Plus)
}

/** Astral, signed in the script, over the cross of light, with the year below. */
@Composable
fun Wordmark(modifier: Modifier = Modifier, crossSize: Dp = 132.dp, showYear: Boolean = true, aside: String? = "MUSIC") {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        AstralEmblem(size = crossSize)
        Spacer(Modifier.height(20.dp))
        Txt("Astral", Type.Inscription)
        if (aside != null) {
            Spacer(Modifier.height(6.dp))
            Txt(aside, Type.Aside, color = Ink.Grey)
        }
        if (showYear) {
            Spacer(Modifier.height(28.dp))
            Txt("MMXXVI", Type.Label.copy(fontSize = 10.sp), color = Ink.Dim)
        }
    }
}

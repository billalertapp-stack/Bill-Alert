package uk.ac.tees.mad.billalert.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val BillAlertPrimary   = Color(0xFF2563EB)   // Blue-600
private val BillAlertSecondary = Color(0xFF7C3AED)   // Violet-600
private val BillAlertTertiary  = Color(0xFF059669)   // Emerald-600
private val BillAlertError     = Color(0xFFDC2626)   // Red-600
private val BillAlertSurface   = Color(0xFFF8FAFC)
private val BillAlertBackground= Color(0xFFF1F5F9)

private val LightColors = lightColorScheme(
    primary          = BillAlertPrimary,
    secondary        = BillAlertSecondary,
    tertiary         = BillAlertTertiary,
    error            = BillAlertError,
    background       = BillAlertBackground,
    surface          = BillAlertSurface,
    onPrimary        = Color.White,
    onSecondary      = Color.White,
    onBackground     = Color(0xFF0F172A),
    onSurface        = Color(0xFF1E293B),
)

@Composable
fun BillAlertTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = BillAlertBackground.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }
    MaterialTheme(
        colorScheme = LightColors,
        typography   = BillAlertTypography,
        content      = content
    )
}
package com.elitec.kingelectronics.infraestructure.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkColorScheme = darkColorScheme(
    primary = GoldPrimary,
    onPrimary = OnGoldText,
    primaryContainer = GoldDark,
    onPrimaryContainer = DarkOnSurface,

    secondary = DarkSecondary,
    onSecondary = OnGoldText,
    secondaryContainer = Color(0xFF3E3E3E),
    onSecondaryContainer = DarkOnSurface,

    tertiary = GoldLight,
    onTertiary = OnGoldText,

    background = DarkBackground,
    onBackground = DarkOnBackground,

    surface = DarkSurface,
    onSurface = DarkOnSurface,

    surfaceVariant = Color(0xFF2C2C2C),
    onSurfaceVariant = Color(0xFFBDBDBD),

    outline = Color(0xFF757575),
    outlineVariant = Color(0xFF424242),

    error = ErrorLight,
    onError = Color(0xFF690005)
)

val LightColorScheme = lightColorScheme(
    primary = GoldPrimary,
    onPrimary = OnGoldText,
    primaryContainer = GoldLight,
    onPrimaryContainer = OnGoldText,

    secondary = LightSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFEFEBE9),
    onSecondaryContainer = LightOnSurface,

    tertiary = GoldDark,
    onTertiary = Color.White,

    background = LightBackground,
    onBackground = LightOnBackground,

    surface = LightSurface,
    onSurface = LightOnSurface,

    surfaceVariant = Color(0xFFF5F5F5),
    onSurfaceVariant = Color(0xFF424242),

    outline = Color(0xFF9E9E9E),
    outlineVariant = Color(0xFFBDBDBD),

    error = ErrorRed,
    onError = Color.White
)

@Composable
fun KingElectronicsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

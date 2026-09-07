package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = MinecraftGreen,
    onPrimary = Color.White,
    primaryContainer = MinecraftDarkGreen,
    onPrimaryContainer = Color.White,
    secondary = MinecraftDiamond,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF004D40),
    onSecondaryContainer = MinecraftDiamond,
    tertiary = MinecraftGold,
    onTertiary = Color.Black,
    background = SurfaceDark,
    onBackground = TextPrimaryDark,
    surface = MinecraftDarkCard,
    onSurface = TextPrimaryDark,
    surfaceVariant = MinecraftDarkSurface,
    onSurfaceVariant = TextSecondaryDark,
    outline = BorderDark
)

private val LightColorScheme = lightColorScheme(
    primary = MinecraftDarkGreen,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFC8E6C9),
    onPrimaryContainer = Color(0xFF1B5E20),
    secondary = Color(0xFF00897B),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFB2DFDB),
    onSecondaryContainer = Color(0xFF004D40),
    tertiary = Color(0xFFE65100),
    onTertiary = Color.White,
    background = Color(0xFFF3F2EE),
    onBackground = Color(0xFF1F1F1F),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1F1F1F),
    surfaceVariant = Color(0xFFE5E3DC),
    onSurfaceVariant = Color(0xFF49454F),
    outline = Color(0xFF8B8B8B)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


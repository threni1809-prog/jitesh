package com.example.ui.sprite

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb

object SpritePalette {
    val COLOR_MAP = mapOf(
        '.' to Color.Transparent,
        // Whites & Highlighting
        'w' to Color(0xFFFFFFFF),
        'q' to Color(0xFFE5E5E5),
        'Q' to Color(0xFFB8B8B8),
        // Iron & Grays
        'i' to Color(0xFFE2E2E2),
        'I' to Color(0xFFB5B5B5),
        'j' to Color(0xFF888888),
        'J' to Color(0xFF4A4A4A),
        'k' to Color(0xFF1E1E1E), // Near black outline
        // Diamonds & Cyan
        'h' to Color(0xFFD4FFFF), // Highlight diamond
        'd' to Color(0xFF4AEDD9), // Diamond cyan
        'D' to Color(0xFF24A292), // Diamond dark cyan
        '1' to Color(0xFF13685E), // Diamond deep shadow
        // Gold & Yellow
        'y' to Color(0xFFFFF7A1), // Light gold / bright yellow
        'g' to Color(0xFFFDB813), // Gold primary
        'G' to Color(0xFFC78B09), // Gold dark
        '2' to Color(0xFF8C5D00), // Gold shadow
        // Wood, Sticks & Oak
        'n' to Color(0xFFD2A368), // Light wood
        'o' to Color(0xFFA5753F), // Oak plank
        'O' to Color(0xFF7A4F20), // Dark oak plank
        's' to Color(0xFF8B5A2B), // Stick brown
        'S' to Color(0xFF5A3816), // Dark stick
        'b' to Color(0xFF38220B), // Deep wood outline
        // Stone & Cobble
        'l' to Color(0xFFB4B4B4), // Light stone
        'c' to Color(0xFF7F7F7F), // Cobble gray
        'C' to Color(0xFF545454), // Dark cobble
        '3' to Color(0xFF333333), // Deep stone shadow
        // Redstone & Crimson
        'r' to Color(0xFFFF3333), // Redstone bright
        'R' to Color(0xFFB81414), // Redstone dark
        '4' to Color(0xFF700B0B), // Redstone shadow
        // Fire, Flame & Orange
        'f' to Color(0xFFFF8800), // Fire orange
        'F' to Color(0xFFFFCC00), // Flame yellow
        '5' to Color(0xFFCC4400), // Deep orange
        // Emerald & Greens
        'e' to Color(0xFF25E36D), // Emerald light
        'E' to Color(0xFF139644), // Emerald dark
        '6' to Color(0xFF0B5E29), // Emerald shadow
        'z' to Color(0xFF78DB34), // Slime / plant light
        'Z' to Color(0xFF4F9B1C), // Slime / plant dark
        // Food, Crust, Leather & Browns
        'm' to Color(0xFFE29B4F), // Bread crust
        'M' to Color(0xFFA86629), // Dark crust
        'p' to Color(0xFF9E5D2E), // Leather
        'P' to Color(0xFF673917), // Dark leather
        'a' to Color(0xFFE02B2B), // Apple red
        'A' to Color(0xFF8F1212), // Apple dark
        // Purple & Magic
        'u' to Color(0xFFBA68C8), // Purple light
        'U' to Color(0xFF7B1FA2), // Purple dark
        '7' to Color(0xFF4A148C), // Deep purple
        // Teal & Ender
        't' to Color(0xFF1DE9B6), // Ender light
        'T' to Color(0xFF00796B), // Ender dark
        '8' to Color(0xFF004D40), // Deep ender teal
        // Blue & Water / Lapis
        'x' to Color(0xFF42A5F5), // Water / blue light
        'X' to Color(0xFF1565C0), // Blue medium
        '9' to Color(0xFF0D47A1)  // Deep blue
    )

    private val bitmapCache = mutableMapOf<String, ImageBitmap>()

    fun getOrCreateBitmap(id: String, pattern: List<String>): ImageBitmap {
        bitmapCache[id]?.let { return it }

        val bitmap = Bitmap.createBitmap(16, 16, Bitmap.Config.ARGB_8888)
        val pixels = IntArray(256)

        for (y in 0 until 16) {
            val row = if (y < pattern.size) pattern[y] else "................"
            for (x in 0 until 16) {
                val char = if (x < row.length) row[x] else '.'
                val color = COLOR_MAP[char] ?: Color.Transparent
                pixels[y * 16 + x] = color.toArgb()
            }
        }

        bitmap.setPixels(pixels, 0, 16, 0, 0, 16, 16)
        val imageBitmap = bitmap.asImageBitmap()
        bitmapCache[id] = imageBitmap
        return imageBitmap
    }
}

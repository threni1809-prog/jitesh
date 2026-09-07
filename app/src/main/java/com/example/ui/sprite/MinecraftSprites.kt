package com.example.ui.sprite

import android.content.Context
import android.content.res.AssetManager
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap

object MinecraftSprites {

    private var assetManager: AssetManager? = null
    private val bitmapCache = ConcurrentHashMap<String, ImageBitmap>()

    private val ALIASES = mapOf(
        "blaze_rod" to "blaze",
        "blaze_powder" to "blaze",
        "slime_ball" to "slimeball",
        "ender_pearl" to "pearl",
        "ender_eye" to "eye_of_ender",
        "comparator" to "redstone_comparator",
        "repeater" to "redstone_repeater",
        "redstone_dust" to "redstone",
        "nether_quartz" to "quartz",
        "red_bed" to "bed"
    )

    fun isInitialized(): Boolean = assetManager != null

    fun init(context: Context) {
        if (assetManager != null) return
        val am = context.applicationContext.assets
        assetManager = am

        // Preload available textures from the official Minecraft resource pack
        try {
            val files = am.list("minecraft/textures") ?: emptyArray()
            for (file in files) {
                if (file.endsWith(".png")) {
                    val key = file.removeSuffix(".png")
                    try {
                        am.open("minecraft/textures/$file").use { stream ->
                            val bmp = BitmapFactory.decodeStream(stream)
                            if (bmp != null) {
                                bitmapCache[key] = bmp.asImageBitmap()
                            }
                        }
                    } catch (_: Exception) {
                        // Continue loading other textures
                    }
                }
            }
        } catch (_: Exception) {
            // Assets list error handled gracefully
        }
    }

    private val allPatterns: Map<String, List<String>> by lazy {
        val merged = mutableMapOf<String, List<String>>()
        merged.putAll(ToolSprites.PATTERNS)
        merged.putAll(BlockSprites.PATTERNS)
        merged.putAll(RedstoneAndFoodSprites.PATTERNS)
        merged.putAll(MaterialSprites.PATTERNS)
        merged
    }

    fun getSprite(itemId: String): ImageBitmap {
        val rawClean = itemId.lowercase().trim()
        val cleanId = ALIASES[rawClean] ?: rawClean

        // 1. Check in-memory bitmap cache
        bitmapCache[cleanId]?.let { return it }

        // 2. Try loading from assets if assetManager is available
        assetManager?.let { am ->
            loadBitmapFromAsset(am, cleanId)?.let { loaded ->
                bitmapCache[cleanId] = loaded
                return loaded
            }
        }

        // 3. Fallback to procedural palette
        val pattern = allPatterns[cleanId] ?: getProceduralPattern(cleanId)
        val generated = SpritePalette.getOrCreateBitmap(cleanId, pattern)
        bitmapCache[cleanId] = generated
        return generated
    }

    private fun loadBitmapFromAsset(am: AssetManager, cleanId: String): ImageBitmap? {
        val candidates = listOf(
            "minecraft/textures/$cleanId.png",
            "minecraft/textures/${cleanId}_front.png",
            "minecraft/textures/${cleanId}_top.png",
            "minecraft/textures/${cleanId}_side.png"
        )
        for (candidate in candidates) {
            try {
                am.open(candidate).use { inputStream ->
                    val bmp = BitmapFactory.decodeStream(inputStream)
                    if (bmp != null) {
                        return bmp.asImageBitmap()
                    }
                }
            } catch (_: IOException) {
                // Continue to next candidate
            }
        }
        return null
    }

    private fun getProceduralPattern(id: String): List<String> {
        // Return thematic authentic 16x16 Minecraft pixel sprites for specialized items
        return when {
            id.contains("bucket") -> listOf(
                "................",
                "................",
                "................",
                "....kJJ..JJk....",
                "...kJIIJJIIJk...",
                "...JIIIIIIIIJ...",
                "...JIIxxxxIIJ...",
                "...JIIxxxxIIJ...",
                "...JIIxxxxIIJ...",
                "....JIIxxIIJ....",
                "....JIIxxIIJ....",
                ".....JIIIIJ.....",
                "......JJJJ......",
                "................",
                "................",
                "................"
            )
            id.contains("quartz") -> listOf(
                "................",
                ".......ww.......",
                "......wwww......",
                ".....wqqqqw.....",
                "....wqqqqqqw....",
                "...wqqqqqqqqw...",
                "..wqqqqqqqqqqw..",
                "...wqqqqqqqqw...",
                "....wqqqqqqw....",
                ".....wqqqqw.....",
                "......wwww......",
                ".......ww.......",
                "................",
                "................",
                "................",
                "................"
            )
            id.contains("pearl") -> listOf(
                "......8888......",
                "....88TTTT88....",
                "...8TTttttTT8...",
                "..8Ttt8888ttT8..",
                ".8Ttt8tttt8ttT8.",
                ".8Ttt88tt88ttT8.",
                "8Ttttt8888ttttT8",
                "8TttttttttttttT8",
                "8TttttttttttttT8",
                "8TttttttttttttT8",
                ".8TttttttttttT8.",
                ".8TttttttttttT8.",
                "..8TttttttttT8..",
                "...8TTttttTT8...",
                "....88TTTT88....",
                "......8888......"
            )
            id.contains("blaze") -> listOf(
                ".......yy.......",
                "......yyyy......",
                ".....yffffy.....",
                ".....yffffy.....",
                "....yyffffyy....",
                "....yyffffyy....",
                "...yyyffffyyy...",
                "...yyyffffyyy...",
                "..yyyyffffyyyy..",
                "..yyyyffffyyyy..",
                "....yyffffyy....",
                "......yyyy......",
                ".......yy.......",
                ".......yy.......",
                ".......yy.......",
                ".......yy......."
            )
            id.contains("emerald") -> listOf(
                "......6666......",
                "....66EeeeE6....",
                "...6EeeewweE6...",
                "..6EeewwwweE6...",
                "..6EeeeeeeeE6...",
                "..6EEeeeeeEE6...",
                "...6EEeeeEE6....",
                "....6EeeeE6.....",
                ".....6EeE6......",
                "......6EE6......",
                ".......66.......",
                "................",
                "................",
                "................",
                "................",
                "................"
            )
            id.contains("stone") -> listOf(
                "3333333333333333",
                "3llllllllllllll3",
                "3lcccccccccccccl3",
                "3lcccccccccccccl3",
                "3lcccccccccccccl3",
                "3lcccccccccccccl3",
                "3lcccccccccccccl3",
                "3lcccccccccccccl3",
                "3lcccccccccccccl3",
                "3lcccccccccccccl3",
                "3lcccccccccccccl3",
                "3lcccccccccccccl3",
                "3lcccccccccccccl3",
                "3lcccccccccccccl3",
                "3lcccccccccccccl3",
                "3333333333333333"
            )
            id.contains("glass") -> listOf(
                "JJJJJJJJJJJJJJJJ",
                "JxxxxxxxxxxxxxxJ",
                "JxwwxxxxxxxxxxxJ",
                "JxxwwxxxxxxxxxxJ",
                "JxxxwwxxxxxxxxxJ",
                "JxxxxxxxxxxxxxxJ",
                "JxxxxxxxxxxxxxxJ",
                "JxxxxxxxwwxxxxxJ",
                "JxxxxxxxxwwxxxxJ",
                "JxxxxxxxxxwwxxxJ",
                "JxxxxxxxxxxxxxxJ",
                "JxxxxxxxxxxxxxxJ",
                "JxxxxxxxxxxxxxxJ",
                "JxxxxxxxxxxxxxxJ",
                "JxxxxxxxxxxxxxxJ",
                "JJJJJJJJJJJJJJJJ"
            )
            id.contains("potion") -> listOf(
                ".......JJ.......",
                "......JssJ......",
                "......JssJ......",
                ".....JqqqqJ.....",
                "....JqqqqqqJ....",
                "...JqqquuqqqJ...",
                "..JqquuuuuuqqJ..",
                "..JqquuuuuuqqJ..",
                "..JqquuuuuuqqJ..",
                "..JqquuuuuuqqJ..",
                "..JqquuuuuuqqJ..",
                "..JqquuuuuuqqJ..",
                "...JqquuuuqqJ...",
                "....JJJJJJJJ....",
                "................",
                "................"
            )
            id.contains("gold") -> listOf(
                "......2222......",
                "....22GgggG2....",
                "...2GgggwwgG2...",
                "..2GggwwwwgG2...",
                "..2GgggggggG2...",
                "..2GGgggggGG2...",
                "...2GGgggGG2....",
                "....2GgggG2.....",
                ".....2GgG2......",
                "......2GG2......",
                ".......22.......",
                "................",
                "................",
                "................",
                "................",
                "................"
            )
            id.contains("iron") -> listOf(
                "......JJJJ......",
                "....JJIIIIJJ....",
                "...JIIIIwwIIJ...",
                "..JIIIwwwwIIJ...",
                "..JIIIIIIIIIJ...",
                "..JJIIIIIIJJJ...",
                "...JJIIIIJJ.....",
                "....JIIIIJ......",
                ".....JIIJ.......",
                "......JJ........",
                "................",
                "................",
                "................",
                "................",
                "................",
                "................"
            )
            else -> listOf(
                "bbbbbbbbbbbbbbbb",
                "bnooooooooooooon",
                "bonnnnnnnnnnnnnb",
                "bonoooooooooooon",
                "bonoooooooooooon",
                "bonoooooooooooon",
                "bonoooooooooooon",
                "bonoooooooooooon",
                "bonoooooooooooon",
                "bonoooooooooooon",
                "bonoooooooooooon",
                "bonoooooooooooon",
                "bonoooooooooooon",
                "bonnnnnnnnnnnnnb",
                "bnooooooooooooon",
                "bbbbbbbbbbbbbbbb"
            )
        }
    }
}

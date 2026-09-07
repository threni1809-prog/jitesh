package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.sprite.MinecraftSprites
import com.example.ui.theme.MinecraftDiamond

/**
 * Apple Precision-Milled Liquid Glass Lens Slot.
 * Features beveled specular rim, convex refraction depth, and tactile elastic stretch.
 */
@Composable
fun MinecraftSlot(
    itemId: String?,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    count: Int = 1,
    isLarge: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val corner = 12.dp

    Box(
        modifier = modifier
            .size(size)
            .testTag(if (itemId != null) "slot_$itemId" else "empty_slot")
            .clip(RoundedCornerShape(corner))
            // Deep refractive glass well
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0x354AEDD9),
                        Color(0x280D1420),
                        Color(0x55050A10)
                    )
                )
            )
            // Specular glass bezel border
            .border(
                width = 1.25.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xE0FFFFFF),
                        Color(0x33FFFFFF),
                        Color(0x15FFFFFF),
                        Color(0x454AEDD9)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                ),
                shape = RoundedCornerShape(corner)
            )
            .drawWithContent {
                drawContent()
                // Top specular lens meniscus
                val highlightH = 1.2.dp.toPx()
                drawRoundRect(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Transparent,
                            Color(0xAAFFFFFF),
                            Color.Transparent
                        )
                    ),
                    topLeft = Offset(4.dp.toPx(), 0.5.dp.toPx()),
                    size = Size(this.size.width - 8.dp.toPx(), highlightH),
                    cornerRadius = CornerRadius(highlightH / 2, highlightH / 2)
                )
            }
            .then(
                if (onClick != null) {
                    Modifier.elasticGlassPress(
                        pressScaleX = 0.90f,
                        pressScaleY = 0.90f,
                        onClick = onClick
                    )
                } else {
                    Modifier
                }
            )
            .padding(if (isLarge) 6.dp else 4.dp),
        contentAlignment = Alignment.Center
    ) {
        if (itemId != null) {
            val sprite = MinecraftSprites.getSprite(itemId)
            Image(
                bitmap = sprite,
                contentDescription = itemId,
                filterQuality = FilterQuality.None,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(if (isLarge) 4.dp else 2.dp)
            )

            if (count > 1) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 2.dp, y = 2.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xCC000000))
                        .border(0.5.dp, Color(0x66FFFFFF), RoundedCornerShape(4.dp))
                        .padding(horizontal = 3.dp, vertical = 0.5.dp)
                ) {
                    Text(
                        text = "$count",
                        color = Color.White,
                        fontSize = if (isLarge) 12.sp else 10.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

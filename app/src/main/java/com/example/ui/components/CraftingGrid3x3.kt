package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.MinecraftAudioHaptics
import com.example.model.CraftingRecipe

@Composable
fun CraftingGrid3x3(
    recipe: CraftingRecipe,
    modifier: Modifier = Modifier,
    onIngredientClick: ((String) -> Unit)? = null
) {
    Box(
        modifier = modifier
            .testTag("crafting_grid_${recipe.id}")
            .background(Color(0xFFC6C6C6), RoundedCornerShape(8.dp))
            .border(3.dp, Color(0xFF555555), RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // 3x3 Grid
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (row in 0..2) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        for (col in 0..2) {
                            val index = row * 3 + col
                            val itemId = recipe.grid.getOrNull(index)
                            MinecraftSlot(
                                itemId = itemId,
                                size = 44.dp,
                                onClick = if (itemId != null) {
                                    {
                                        MinecraftAudioHaptics.playClickSound()
                                        MinecraftAudioHaptics.hapticItemPlaced()
                                        onIngredientClick?.invoke(itemId)
                                    }
                                } else null
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Crafting Arrow
            CraftingArrow(modifier = Modifier.size(32.dp, 24.dp))

            Spacer(modifier = Modifier.width(16.dp))

            // Result Slot
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MinecraftSlot(
                    itemId = recipe.id,
                    size = 56.dp,
                    count = recipe.outputCount,
                    isLarge = true,
                    onClick = {
                        MinecraftAudioHaptics.playCraftingSound()
                        MinecraftAudioHaptics.hapticRecipeCrafted()
                    }
                )
                if (recipe.outputCount > 1) {
                    Text(
                        text = "Yields ${recipe.outputCount}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF333333),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CraftingArrow(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val arrowColor = Color(0xFF555555)
        val shaftHeight = size.height * 0.4f
        val shaftWidth = size.width * 0.55f
        val headWidth = size.width * 0.45f

        val topShaft = (size.height - shaftHeight) / 2f
        val bottomShaft = topShaft + shaftHeight

        // Arrow Shaft
        drawRect(
            color = arrowColor,
            topLeft = Offset(0f, topShaft),
            size = androidx.compose.ui.geometry.Size(shaftWidth, shaftHeight)
        )

        // Arrow Head
        val path = Path().apply {
            moveTo(shaftWidth, 0f)
            lineTo(size.width, size.height / 2f)
            lineTo(shaftWidth, size.height)
            close()
        }
        drawPath(path, arrowColor)
    }
}

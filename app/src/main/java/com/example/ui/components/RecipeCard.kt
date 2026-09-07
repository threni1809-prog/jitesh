package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CraftingRecipe
import com.example.ui.theme.MinecraftDiamond
import com.example.ui.theme.MinecraftGold
import com.example.ui.theme.MinecraftGreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeCard(
    recipe: CraftingRecipe,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Apple Liquid Glass Tile with interactive elastic squash-and-stretch
    LiquidGlassPanel(
        modifier = modifier
            .fillMaxWidth()
            .testTag("recipe_card_${recipe.id}")
            .elasticGlassPress(
                pressScaleX = 0.96f,
                pressScaleY = 0.96f,
                onClick = onClick
            ),
        shape = RoundedCornerShape(20.dp),
        borderHighlightColor = Color(0xD0FFFFFF)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Milled Glass Lens Slot
            MinecraftSlot(
                itemId = recipe.id,
                size = 54.dp,
                count = recipe.outputCount,
                isLarge = true,
                onClick = onClick
            )

            Spacer(modifier = Modifier.width(14.dp))

            // Details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = recipe.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = recipe.description,
                    fontSize = 12.sp,
                    color = Color(0xFFA5B4CB),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Ingredients Glass Pills & Category Badge
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Category Badge
                    LiquidGlassBadge(
                        text = recipe.category.displayName,
                        accentColor = if (recipe.category == com.example.model.RecipeCategory.COMBAT) {
                            Color(0xFFFF5252)
                        } else if (recipe.category == com.example.model.RecipeCategory.REDSTONE) {
                            Color(0xFFFF3366)
                        } else if (recipe.category == com.example.model.RecipeCategory.BREWING) {
                            Color(0xFFB388FF)
                        } else {
                            MinecraftDiamond
                        }
                    )

                    // Ingredients Count Pills
                    recipe.ingredients.take(3).forEach { ing ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0x18FFFFFF))
                                .border(0.5.dp, Color(0x30FFFFFF), RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "${ing.itemName} x${ing.count}",
                                fontSize = 10.5.sp,
                                color = Color(0xFFCDD7E6),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    if (recipe.ingredients.size > 3) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0x18FFFFFF))
                                .border(0.5.dp, Color(0x30FFFFFF), RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "+${recipe.ingredients.size - 3} more",
                                fontSize = 10.5.sp,
                                color = MinecraftGreen,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Favorite Button with Elastic Stretch Bounce
            val favScale by animateFloatAsState(
                targetValue = if (isFavorite) 1.15f else 1.0f,
                animationSpec = spring(
                    dampingRatio = 0.5f, // Bouncy rubber-band spring!
                    stiffness = 400f
                ),
                label = "fav_scale"
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .scale(favScale)
                    .clip(CircleShape)
                    .background(if (isFavorite) Color(0x30FFD700) else Color(0x14FFFFFF))
                    .border(
                        1.dp,
                        if (isFavorite) MinecraftGold.copy(alpha = 0.8f) else Color(0x28FFFFFF),
                        CircleShape
                    )
                    .elasticGlassPress(
                        pressScaleX = 0.85f,
                        pressScaleY = 0.85f,
                        onClick = onToggleFavorite
                    )
                    .testTag("favorite_button_${recipe.id}"),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Star else Icons.Outlined.StarBorder,
                    contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                    tint = if (isFavorite) MinecraftGold else Color(0x88FFFFFF),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

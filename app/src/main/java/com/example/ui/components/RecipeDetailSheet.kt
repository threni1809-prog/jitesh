package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CraftingRecipe
import com.example.model.RecipeDatabase
import com.example.ui.sprite.MinecraftSprites
import com.example.ui.theme.MinecraftDiamond
import com.example.ui.theme.MinecraftGold
import com.example.ui.theme.MinecraftGreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RecipeDetailSheet(
    recipe: CraftingRecipe,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onDismiss: () -> Unit,
    onNavigateToRecipe: (String) -> Unit,
    onOpenInSandbox: (CraftingRecipe) -> Unit = {},
    sheetState: SheetState
) {
    var craftMultiplier by remember(recipe.id) { mutableIntStateOf(1) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color(0xF20B1017), // Apple visionOS Deep Frosted Glass
        dragHandle = {
            // Apple Glass Drag Handle
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .width(40.dp)
                    .height(4.5.dp)
                    .clip(CircleShape)
                    .background(Color(0x60FFFFFF))
            )
        },
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        modifier = Modifier.testTag("recipe_detail_sheet")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            // Header Row with Apple Glass Milled Lens
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                Brush.radialGradient(
                                    listOf(
                                        Color(0x404AEDD9),
                                        Color(0x20152230),
                                        Color(0x60050A10)
                                    )
                                )
                            )
                            .border(
                                1.25.dp,
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xF0FFFFFF),
                                        Color(0x35FFFFFF),
                                        Color(0x404AEDD9)
                                    )
                                ),
                                RoundedCornerShape(16.dp)
                            )
                            .padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            bitmap = MinecraftSprites.getSprite(recipe.id),
                            contentDescription = recipe.name,
                            filterQuality = FilterQuality.None,
                            modifier = Modifier.size(44.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = recipe.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Text(
                            text = "minecraft:${recipe.id}",
                            fontSize = 12.sp,
                            fontFamily = FontFamily.Monospace,
                            color = Color(0xFF8FA4BF)
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Favorite Star with Elastic Stretch Bounce
                    val favScale by animateFloatAsState(
                        targetValue = if (isFavorite) 1.15f else 1.0f,
                        animationSpec = spring(dampingRatio = 0.5f, stiffness = 400f),
                        label = "fav_scale"
                    )

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .scale(favScale)
                            .clip(CircleShape)
                            .background(if (isFavorite) Color(0x33FFD700) else Color(0x18FFFFFF))
                            .border(
                                1.dp,
                                if (isFavorite) MinecraftGold else Color(0x30FFFFFF),
                                CircleShape
                            )
                            .elasticGlassPress(
                                pressScaleX = 0.85f,
                                pressScaleY = 0.85f,
                                onClick = onToggleFavorite
                            )
                            .testTag("detail_favorite_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Star else Icons.Outlined.StarBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) MinecraftGold else Color(0x99FFFFFF),
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // Close Button
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0x18FFFFFF))
                            .border(1.dp, Color(0x30FFFFFF), CircleShape)
                            .elasticGlassPress(
                                pressScaleX = 0.85f,
                                pressScaleY = 0.85f,
                                onClick = onDismiss
                            )
                            .testTag("close_detail_sheet"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Description and Stats
            Text(
                text = recipe.description,
                fontSize = 14.sp,
                color = Color(0xFFCDD9E8),
                lineHeight = 20.sp
            )

            if (recipe.statInfo != null) {
                Spacer(modifier = Modifier.height(8.dp))
                LiquidGlassPanel(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    borderHighlightColor = MinecraftGreen
                ) {
                    Text(
                        text = recipe.statInfo,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MinecraftGreen,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Crafting Matrix Title
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Crafting Table (3x3 Matrix)",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Tap ingredient to inspect",
                    fontSize = 11.5.sp,
                    color = Color(0xFF8899AF)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 3x3 Grid Centered with Apple Milled Glass Slots
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CraftingGrid3x3(
                    recipe = recipe,
                    onIngredientClick = { clickedItemId ->
                        val targetRecipe = RecipeDatabase.findById(clickedItemId)
                        if (targetRecipe != null) {
                            onNavigateToRecipe(clickedItemId)
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Interactive Crafting Calculator in Apple Liquid Glass Panel
            LiquidGlassPanel(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                borderHighlightColor = Color(0xC0FFFFFF)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Craft Calculator",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        // Multiplier controls with Elastic Press
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(Color(0x22FFFFFF))
                                    .border(1.dp, Color(0x44FFFFFF), CircleShape)
                                    .elasticGlassPress(
                                        pressScaleX = 0.85f,
                                        pressScaleY = 0.85f,
                                        onClick = { if (craftMultiplier > 1) craftMultiplier-- }
                                    )
                                    .testTag("decrease_craft_count"),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Remove,
                                    contentDescription = "Decrease",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }

                            Text(
                                text = "$craftMultiplier craft (${recipe.outputCount * craftMultiplier})",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 6.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(Color(0x22FFFFFF))
                                    .border(1.dp, Color(0x44FFFFFF), CircleShape)
                                    .elasticGlassPress(
                                        pressScaleX = 0.85f,
                                        pressScaleY = 0.85f,
                                        onClick = { if (craftMultiplier < 64) craftMultiplier++ }
                                    )
                                    .testTag("increase_craft_count"),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = "Increase",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    // Quick multipliers pills with Elastic Stretch
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        listOf(1, 4, 16, 64).forEach { num ->
                            val isSelected = craftMultiplier == num
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(
                                        if (isSelected) MinecraftGreen.copy(alpha = 0.45f) else Color(0x18FFFFFF)
                                    )
                                    .border(
                                        1.dp,
                                        if (isSelected) MinecraftGreen else Color(0x28FFFFFF),
                                        CircleShape
                                    )
                                    .elasticGlassPress(
                                        pressScaleX = 0.88f,
                                        pressScaleY = 0.88f,
                                        onClick = { craftMultiplier = num }
                                    )
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "x$num",
                                    fontSize = 11.5.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else Color(0xFFBAC5D6)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Total Ingredients Required:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFA5B4C9)
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        recipe.ingredients.forEach { ing ->
                            val totalQty = ing.count * craftMultiplier
                            val hasRecipe = RecipeDatabase.findById(ing.itemId) != null

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0x18FFFFFF))
                                    .border(1.dp, Color(0x28FFFFFF), RoundedCornerShape(10.dp))
                                    .then(
                                        if (hasRecipe) {
                                            Modifier.elasticGlassPress(
                                                pressScaleX = 0.92f,
                                                pressScaleY = 0.92f,
                                                onClick = { onNavigateToRecipe(ing.itemId) }
                                            )
                                        } else {
                                            Modifier
                                        }
                                    )
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                ) {
                                    MinecraftSlot(itemId = ing.itemId, size = 26.dp)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "${ing.itemName} x$totalQty",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = if (hasRecipe) MinecraftDiamond else Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // "Open in Crafting Sandbox" button in vivid Apple Emerald Glass with Elastic Press
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xFF2E7D32),
                                Color(0xFF00897B)
                            )
                        )
                    )
                    .border(
                        1.25.dp,
                        Brush.linearGradient(
                            listOf(
                                Color(0xE0FFFFFF),
                                MinecraftDiamond,
                                Color(0x30FFFFFF)
                            )
                        ),
                        RoundedCornerShape(26.dp)
                    )
                    .elasticGlassPress(
                        pressScaleX = 0.94f,
                        pressScaleY = 0.94f,
                        onClick = {
                            onOpenInSandbox(recipe)
                            onDismiss()
                        }
                    )
                    .testTag("open_in_sandbox_button"),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🧪 Open in Crafting Sandbox",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

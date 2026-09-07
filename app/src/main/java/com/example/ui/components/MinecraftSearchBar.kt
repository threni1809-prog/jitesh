package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.RecipeCategory
import com.example.ui.theme.MinecraftDiamond
import com.example.ui.theme.MinecraftGold
import com.example.ui.theme.MinecraftGreen

@Composable
fun MinecraftSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    selectedCategory: RecipeCategory,
    onCategorySelected: (RecipeCategory) -> Unit,
    favoriteCount: Int,
    resultCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        // Apple Vision Liquid Glass Search Capsule
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("recipe_search_input"),
            placeholder = {
                Text(
                    "Search item or ingredient...",
                    color = Color(0xFF8899B0),
                    fontSize = 14.sp
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MinecraftDiamond,
                    modifier = Modifier.size(20.dp)
                )
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(
                        onClick = { onQueryChange("") },
                        modifier = Modifier
                            .size(48.dp)
                            .testTag("clear_search_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear search",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            },
            shape = RoundedCornerShape(26.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0x28FFFFFF),
                unfocusedContainerColor = Color(0x16FFFFFF),
                focusedBorderColor = MinecraftDiamond,
                unfocusedBorderColor = Color(0x40FFFFFF),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedPlaceholderColor = Color(0xFF8899B0),
                unfocusedPlaceholderColor = Color(0xFF8899B0)
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Horizontally Scrolling Apple Liquid Glass Category Pills with Elastic Stretch
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RecipeCategory.entries.forEach { category ->
                val isSelected = category == selectedCategory

                val scaleX by animateFloatAsState(
                    targetValue = if (isSelected) 1.05f else 1.0f,
                    animationSpec = spring(dampingRatio = 0.6f, stiffness = 400f),
                    label = "pill_scale_x"
                )

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(
                            if (isSelected) {
                                Brush.horizontalGradient(
                                    listOf(
                                        MinecraftGreen.copy(alpha = 0.45f),
                                        MinecraftDiamond.copy(alpha = 0.35f)
                                    )
                                )
                            } else {
                                Brush.linearGradient(listOf(Color(0x14FFFFFF), Color(0x08FFFFFF)))
                            }
                        )
                        .border(
                            width = 1.dp,
                            brush = if (isSelected) {
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xF0FFFFFF),
                                        MinecraftDiamond,
                                        Color(0x554AEDD9)
                                    )
                                )
                            } else {
                                Brush.linearGradient(
                                    listOf(
                                        Color(0x35FFFFFF),
                                        Color(0x12FFFFFF)
                                    )
                                )
                            },
                            shape = CircleShape
                        )
                        .elasticGlassPress(
                            pressScaleX = 0.90f,
                            pressScaleY = 0.90f,
                            onClick = { onCategorySelected(category) }
                        )
                        .padding(horizontal = 14.dp, vertical = 7.dp)
                        .testTag("category_chip_${category.name.lowercase()}"),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (category == RecipeCategory.FAVORITES) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = if (isSelected) MinecraftGold else MinecraftGold.copy(alpha = 0.8f),
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = if (favoriteCount > 0) "Favorites ($favoriteCount)" else "Favorites",
                                fontSize = 12.5.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) Color.White else Color(0xFFBAC7DA)
                            )
                        } else {
                            Text(
                                text = category.displayName,
                                fontSize = 12.5.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) Color.White else Color(0xFFBAC7DA)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Result Count Specular Pill
        Row(
            modifier = Modifier.padding(start = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(MinecraftGreen)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "$resultCount recipes cataloged",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF8E9EB5)
            )
        }
    }
}

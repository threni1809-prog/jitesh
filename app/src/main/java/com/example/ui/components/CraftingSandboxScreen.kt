package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.MinecraftAudioHaptics
import com.example.model.CraftingRecipe
import com.example.model.RecipeDatabase
import com.example.model.SandboxCategory
import com.example.model.SandboxDatabase
import com.example.model.SandboxItem
import com.example.ui.sprite.MinecraftSprites
import com.example.ui.theme.MinecraftDiamond
import com.example.ui.theme.MinecraftGold
import com.example.ui.theme.MinecraftGreen
import kotlin.math.roundToInt

/**
 * State of an active drag-and-drop gesture in the crafting sandbox.
 */
data class DragDropSession(
    val itemId: String,
    val sourceSlotIndex: Int? = null,
    val globalPos: Offset = Offset.Zero,
    val hoveredSlotIndex: Int? = null
)

@Composable
fun CraftingSandboxScreen(
    gridState: List<String?>,
    onSlotSelected: (Int) -> Unit,
    selectedSlotIndex: Int,
    onSetSlotItem: (Int, String?) -> Unit,
    onClearGrid: () -> Unit,
    onLoadRecipe: (CraftingRecipe) -> Unit,
    matchedRecipe: CraftingRecipe?,
    modifier: Modifier = Modifier,
    onMoveOrSwapSlot: (Int, Int) -> Unit = { _, _ -> }
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(SandboxCategory.ALL) }
    var isSoundActive by remember { mutableStateOf(MinecraftAudioHaptics.isSoundEnabled) }

    // Track active drag session
    var dragSession by remember { mutableStateOf<DragDropSession?>(null) }
    val slotBounds = remember { mutableStateMapOf<Int, Rect>() }
    var rootScreenOffset by remember { mutableStateOf(Offset.Zero) }

    // Track previously synthesized recipe to trigger crafting chime and celebratory haptics
    var lastCompletedRecipeId by remember { mutableStateOf<String?>(matchedRecipe?.id) }

    LaunchedEffect(matchedRecipe?.id) {
        val newId = matchedRecipe?.id
        if (newId != null && newId != lastCompletedRecipeId) {
            // Authentic Minecraft Crafting Completion: Pop + Chime Pling sound + rich celebratory haptic feedback
            MinecraftAudioHaptics.playCraftingSound()
            MinecraftAudioHaptics.hapticRecipeCrafted()
        }
        lastCompletedRecipeId = newId
    }

    val filteredItems = remember(searchQuery, selectedCategory) {
        val query = searchQuery.trim().lowercase()
        SandboxDatabase.ITEMS.filter { item ->
            val matchesCategory = selectedCategory == SandboxCategory.ALL || item.category == selectedCategory
            val matchesQuery = query.isEmpty() ||
                    item.name.lowercase().contains(query) ||
                    item.id.lowercase().contains(query)
            matchesCategory && matchesQuery
        }
    }

    val presetRecipes = remember {
        listOf(
            "crafting_table" to "Crafting Table",
            "furnace" to "Furnace",
            "chest" to "Chest",
            "tnt" to "TNT",
            "diamond_sword" to "Diamond Sword",
            "shield" to "Shield",
            "piston" to "Piston",
            "redstone_repeater" to "Repeater",
            "anvil" to "Anvil",
            "golden_apple" to "Golden Apple"
        )
    }

    // Drag handlers
    val onStartDrag: (String, Int?, Offset) -> Unit = { itemId: String, sourceSlot: Int?, startPos: Offset ->
        MinecraftAudioHaptics.playPopSound()
        MinecraftAudioHaptics.hapticItemPickup()
        dragSession = DragDropSession(
            itemId = itemId,
            sourceSlotIndex = sourceSlot,
            globalPos = startPos,
            hoveredSlotIndex = null
        )
    }

    val onUpdateDrag: (Offset) -> Unit = { delta: Offset ->
        dragSession?.let { current ->
            val newPos = current.globalPos + delta
            val newHovered = slotBounds.entries.firstOrNull { (_, rect) ->
                rect.contains(newPos)
            }?.key

            if (newHovered != current.hoveredSlotIndex) {
                if (newHovered != null) {
                    // Tactile tick & subtle wooden sound when crossing into slot boundary
                    MinecraftAudioHaptics.playDragTickSound()
                    MinecraftAudioHaptics.hapticSlotHover()
                }
            }

            dragSession = current.copy(
                globalPos = newPos,
                hoveredSlotIndex = newHovered
            )
        }
    }

    val onEndDrag: () -> Unit = {
        dragSession?.let { session ->
            val target = session.hoveredSlotIndex
            if (target != null) {
                if (session.sourceSlotIndex != null && session.sourceSlotIndex != target) {
                    // Swapped or moved between slots
                    onMoveOrSwapSlot(session.sourceSlotIndex, target)
                    MinecraftAudioHaptics.playClickSound()
                    MinecraftAudioHaptics.hapticItemPlaced()
                } else {
                    // Placed into target slot
                    onSetSlotItem(target, session.itemId)
                    MinecraftAudioHaptics.playClickSound()
                    MinecraftAudioHaptics.hapticItemPlaced()
                }
            } else if (session.sourceSlotIndex != null) {
                // Dragged out of matrix -> remove/clear item from slot
                onSetSlotItem(session.sourceSlotIndex, null)
                MinecraftAudioHaptics.playPopSound()
                MinecraftAudioHaptics.hapticItemPlaced()
            }
        }
        dragSession = null
    }

    val onCancelDrag: () -> Unit = {
        dragSession = null
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                rootScreenOffset = coordinates.positionInRoot()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- 1. Top Bar & Action Row ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Crafting Matrix",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        LiquidGlassBadge(
                            text = "Haptic Tactile",
                            accentColor = MinecraftDiamond
                        )
                    }
                    Text(
                        text = "Drag or tap items into the 3x3 slots",
                        fontSize = 12.sp,
                        color = Color(0xFF9FB2CC)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Audio SFX Toggle Pill
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (isSoundActive) Color(0x254AEDD9) else Color(0x18FFFFFF))
                            .border(
                                1.dp,
                                if (isSoundActive) MinecraftDiamond.copy(alpha = 0.6f) else Color(0x30FFFFFF),
                                RoundedCornerShape(14.dp)
                            )
                            .elasticGlassPress(
                                pressScaleX = 0.88f,
                                pressScaleY = 0.88f,
                                onClick = {
                                    MinecraftAudioHaptics.isSoundEnabled = !MinecraftAudioHaptics.isSoundEnabled
                                    isSoundActive = MinecraftAudioHaptics.isSoundEnabled
                                    if (isSoundActive) {
                                        MinecraftAudioHaptics.playClickSound()
                                    }
                                    MinecraftAudioHaptics.hapticItemPlaced()
                                }
                            )
                            .padding(horizontal = 10.dp, vertical = 7.dp)
                            .testTag("sfx_toggle_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (isSoundActive) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                                contentDescription = if (isSoundActive) "Sound On" else "Sound Muted",
                                tint = if (isSoundActive) MinecraftDiamond else Color(0x80FFFFFF),
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isSoundActive) "SFX" else "MUTED",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSoundActive) MinecraftDiamond else Color(0x80FFFFFF)
                            )
                        }
                    }

                    // Quick Clear Button with Elastic Stretch
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0x22FF4757))
                            .border(1.dp, Color(0x66FF4757), RoundedCornerShape(14.dp))
                            .elasticGlassPress(
                                pressScaleX = 0.88f,
                                pressScaleY = 0.88f,
                                onClick = {
                                    onClearGrid()
                                    MinecraftAudioHaptics.playPopSound()
                                    MinecraftAudioHaptics.hapticItemPlaced()
                                }
                            )
                            .padding(horizontal = 10.dp, vertical = 7.dp)
                            .testTag("sandbox_clear_grid_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.DeleteSweep,
                                contentDescription = "Clear Grid",
                                tint = Color(0xFFFF6B6B),
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Clear",
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFF6B6B)
                            )
                        }
                    }
                }
            }

            // --- 2. Interactive 3x3 Matrix & Synthesis Chamber (Apple Liquid Glass) ---
            LiquidGlassPanel(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(24.dp),
                borderHighlightColor = Color(0xE0FFFFFF)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // 3x3 Milled Glass Slot Grid
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            for (row in 0..2) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    for (col in 0..2) {
                                        val index = row * 3 + col
                                        val isSelected = index == selectedSlotIndex
                                        val itemId = gridState.getOrNull(index)
                                        val isHovered = dragSession?.hoveredSlotIndex == index

                                        AppleGlassSlot(
                                            index = index,
                                            itemId = itemId,
                                            isSelected = isSelected,
                                            isHovered = isHovered,
                                            onClick = {
                                                onSlotSelected(index)
                                                MinecraftAudioHaptics.playClickSound()
                                                MinecraftAudioHaptics.hapticItemPlaced()
                                            },
                                            onClear = {
                                                onSetSlotItem(index, null)
                                                MinecraftAudioHaptics.playPopSound()
                                                MinecraftAudioHaptics.hapticItemPlaced()
                                            },
                                            onPositioned = { rect ->
                                                slotBounds[index] = rect
                                            },
                                            onStartDrag = onStartDrag,
                                            onUpdateDrag = onUpdateDrag,
                                            onEndDrag = onEndDrag,
                                            onCancelDrag = onCancelDrag
                                        )
                                    }
                                }
                            }
                        }

                        // Fluid Refraction Arrow with ambient cyan pulse
                        Box(
                            modifier = Modifier.padding(horizontal = 14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Synthesis arrow",
                                tint = if (matchedRecipe != null) MinecraftDiamond else Color(0x60FFFFFF),
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        // Result Synthesis Chamber
                        AppleGlassResultChamber(
                            matchedRecipe = matchedRecipe,
                            onClick = {
                                if (matchedRecipe != null) {
                                    MinecraftAudioHaptics.playCraftingSound()
                                    MinecraftAudioHaptics.hapticRecipeCrafted()
                                }
                            }
                        )
                    }

                    // Presets Quick-Test Chips with Elastic Stretch
                    Spacer(modifier = Modifier.height(14.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(presetRecipes) { (recipeId, label) ->
                            val recipe = remember(recipeId) { RecipeDatabase.getRecipeById(recipeId) }
                            if (recipe != null) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0x1CFFFFFF))
                                        .border(
                                            1.dp,
                                            Brush.linearGradient(
                                                listOf(
                                                    Color(0x80FFFFFF),
                                                    Color(0x20FFFFFF)
                                                )
                                            ),
                                            RoundedCornerShape(12.dp)
                                        )
                                        .elasticGlassPress(
                                            pressScaleX = 0.90f,
                                            pressScaleY = 0.90f,
                                            onClick = {
                                                onLoadRecipe(recipe)
                                                // Recipe complete chime & haptics are handled by LaunchedEffect
                                            }
                                        )
                                        .padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Image(
                                            bitmap = MinecraftSprites.getSprite(recipe.id),
                                            contentDescription = label,
                                            filterQuality = FilterQuality.None,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = label,
                                            fontSize = 11.5.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // --- 3. Result Inspection Banner (When recipe is synthesized) ---
            AnimatedVisibility(
                visible = matchedRecipe != null,
                enter = fadeIn() + scaleIn(
                    initialScale = 0.88f,
                    animationSpec = spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessMediumLow)
                ),
                exit = fadeOut() + scaleOut(targetScale = 0.88f)
            ) {
                matchedRecipe?.let { recipe ->
                    val isBlock = SandboxDatabase.isBlockItem(recipe.id) || recipe.category == com.example.model.RecipeCategory.BUILDING

                    LiquidGlassPanel(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(18.dp),
                        borderHighlightColor = if (isBlock) MinecraftDiamond else MinecraftGreen
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0x35000000))
                                    .border(1.2.dp, Color(0x80FFFFFF), RoundedCornerShape(12.dp))
                                    .clickable {
                                        MinecraftAudioHaptics.playCraftingSound()
                                        MinecraftAudioHaptics.hapticRecipeCrafted()
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    bitmap = MinecraftSprites.getSprite(recipe.id),
                                    contentDescription = recipe.name,
                                    filterQuality = FilterQuality.None,
                                    modifier = Modifier.size(34.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = recipe.name,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    LiquidGlassBadge(
                                        text = if (isBlock) "BLOCK" else "ITEM",
                                        accentColor = if (isBlock) MinecraftDiamond else MinecraftGold
                                    )
                                }
                                Text(
                                    text = recipe.description,
                                    fontSize = 12.sp,
                                    color = Color(0xFFC0CCDE),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                if (recipe.statInfo != null) {
                                    Text(
                                        text = recipe.statInfo,
                                        fontSize = 11.sp,
                                        color = MinecraftGreen,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // --- 4. Catalog Header, Search & Category Filters ---
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Item Catalog (${filteredItems.size})",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                // Category selector tabs with elastic stretch
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    items(SandboxCategory.entries) { cat ->
                        val isCatSelected = cat == selectedCategory

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    if (isCatSelected) {
                                        Brush.horizontalGradient(
                                            listOf(
                                                MinecraftGreen.copy(alpha = 0.45f),
                                                MinecraftDiamond.copy(alpha = 0.35f)
                                            )
                                        )
                                    } else {
                                        Brush.linearGradient(listOf(Color(0x18FFFFFF), Color(0x0CFFFFFF)))
                                    }
                                )
                                .border(
                                    1.dp,
                                    if (isCatSelected) MinecraftGreen else Color(0x28FFFFFF),
                                    CircleShape
                                )
                                .elasticGlassPress(
                                    pressScaleX = 0.90f,
                                    pressScaleY = 0.90f,
                                    onClick = {
                                        selectedCategory = cat
                                        MinecraftAudioHaptics.playClickSound()
                                        MinecraftAudioHaptics.hapticSlotHover()
                                    }
                                )
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "${cat.icon} ${cat.displayName}",
                                fontSize = 11.sp,
                                fontWeight = if (isCatSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isCatSelected) Color.White else Color(0xFFA5B2C9)
                            )
                        }
                    }
                }
            }

            // Search Input
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search items (drag or tap to place)...", fontSize = 12.5.sp) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MinecraftDiamond,
                        modifier = Modifier.size(18.dp)
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0x26FFFFFF),
                    unfocusedContainerColor = Color(0x14FFFFFF),
                    focusedBorderColor = MinecraftDiamond,
                    unfocusedBorderColor = Color(0x35FFFFFF),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedPlaceholderColor = Color(0xFF7A889B),
                    unfocusedPlaceholderColor = Color(0xFF7A889B)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("sandbox_search_input")
            )

            // --- 5. All Items Grid with Elastic Tactile Press & Drag Support ---
            Spacer(modifier = Modifier.height(8.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 64.dp),
                contentPadding = PaddingValues(bottom = 28.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .testTag("sandbox_item_palette_grid")
            ) {
                items(
                    items = filteredItems,
                    key = { it.id }
                ) { item ->
                    AppleGlassPaletteItem(
                        item = item,
                        onClick = {
                            // Tap to place item into currently selected slot
                            onSetSlotItem(selectedSlotIndex, item.id)
                            MinecraftAudioHaptics.playClickSound()
                            MinecraftAudioHaptics.hapticItemPlaced()

                            // Advance to next empty slot
                            val nextEmpty = (selectedSlotIndex + 1 until 9).firstOrNull { gridState[it] == null }
                                ?: (0 until 9).firstOrNull { gridState[it] == null }
                                ?: ((selectedSlotIndex + 1) % 9)
                            onSlotSelected(nextEmpty)
                        },
                        onStartDrag = onStartDrag,
                        onUpdateDrag = onUpdateDrag,
                        onEndDrag = onEndDrag,
                        onCancelDrag = onCancelDrag
                    )
                }
            }
        }

        // --- 6. Floating Item Avatar during Drag-and-Drop ---
        dragSession?.let { session ->
            val localX = session.globalPos.x - rootScreenOffset.x
            val localY = session.globalPos.y - rootScreenOffset.y

            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            (localX - 32.dp.toPx()).roundToInt(),
                            (localY - 48.dp.toPx()).roundToInt()
                        )
                    }
                    .size(64.dp)
                    .graphicsLayer {
                        scaleX = 1.25f
                        scaleY = 1.25f
                        rotationZ = 6f
                        shadowElevation = 18f
                    }
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xEE0B1522))
                    .border(
                        1.5.dp,
                        Brush.linearGradient(
                            listOf(
                                Color.White,
                                MinecraftDiamond,
                                Color(0x604AEDD9)
                            )
                        ),
                        RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    bitmap = MinecraftSprites.getSprite(session.itemId),
                    contentDescription = "Dragging ${session.itemId}",
                    filterQuality = FilterQuality.None,
                    modifier = Modifier.size(46.dp)
                )
            }
        }
    }
}

/**
 * Precision-Milled Apple Liquid Glass Slot with Elastic Tactile Response and Drag-and-Drop Sensing.
 */
@Composable
private fun AppleGlassSlot(
    index: Int,
    itemId: String?,
    isSelected: Boolean,
    isHovered: Boolean,
    onClick: () -> Unit,
    onClear: () -> Unit,
    onPositioned: (Rect) -> Unit,
    onStartDrag: (String, Int?, Offset) -> Unit,
    onUpdateDrag: (Offset) -> Unit,
    onEndDrag: () -> Unit,
    onCancelDrag: () -> Unit
) {
    var slotGlobalPos by remember { mutableStateOf(Offset.Zero) }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse_border")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_alpha"
    )

    val slotScale by animateFloatAsState(
        targetValue = if (isHovered) 1.14f else 1.0f,
        animationSpec = spring(dampingRatio = 0.62f, stiffness = 420f),
        label = "slot_hover_scale"
    )

    Box(
        modifier = Modifier
            .size(54.dp)
            .scale(slotScale)
            .onGloballyPositioned { coordinates ->
                slotGlobalPos = coordinates.positionInRoot()
                onPositioned(coordinates.boundsInRoot())
            }
            .clip(RoundedCornerShape(14.dp))
            // Lens well refraction
            .background(
                brush = Brush.radialGradient(
                    colors = when {
                        isHovered -> listOf(
                            Color(0x904AEDD9),
                            Color(0x40102535),
                            Color(0x80050A10)
                        )
                        isSelected -> listOf(
                            Color(0x504AEDD9),
                            Color(0x30101825),
                            Color(0x70050A10)
                        )
                        else -> listOf(
                            Color(0x22FFFFFF),
                            Color(0x20101825),
                            Color(0x55050A10)
                        )
                    }
                )
            )
            // Specular lens rim border
            .border(
                width = if (isHovered || isSelected) 2.dp else 1.25.dp,
                brush = when {
                    isHovered -> Brush.linearGradient(
                        listOf(
                            Color.White,
                            MinecraftDiamond,
                            Color(0xFF00FFCC)
                        )
                    )
                    isSelected -> Brush.linearGradient(
                        listOf(
                            Color.White,
                            MinecraftDiamond.copy(alpha = glowAlpha),
                            Color(0x604AEDD9)
                        )
                    )
                    else -> Brush.linearGradient(
                        listOf(
                            Color(0xD0FFFFFF),
                            Color(0x28FFFFFF),
                            Color(0x354AEDD9)
                        )
                    )
                },
                shape = RoundedCornerShape(14.dp)
            )
            .drawWithContent {
                drawContent()
                // Top meniscus reflection
                val h = 1.2.dp.toPx()
                drawRoundRect(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Transparent,
                            if (isHovered || isSelected) MinecraftDiamond.copy(alpha = 0.9f) else Color(0x88FFFFFF),
                            Color.Transparent
                        )
                    ),
                    topLeft = Offset(4.dp.toPx(), 0.5.dp.toPx()),
                    size = Size(size.width - 8.dp.toPx(), h),
                    cornerRadius = CornerRadius(h / 2, h / 2)
                )
            }
            .pointerInput(itemId, index) {
                if (itemId != null) {
                    detectDragGesturesAfterLongPress(
                        onDragStart = { offset ->
                            onStartDrag(itemId, index, slotGlobalPos + offset)
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            onUpdateDrag(dragAmount)
                        },
                        onDragEnd = { onEndDrag() },
                        onDragCancel = { onCancelDrag() }
                    )
                }
            }
            .elasticGlassPress(
                pressScaleX = 0.88f,
                pressScaleY = 0.88f,
                onClick = onClick
            )
            .testTag("sandbox_slot_$index"),
        contentAlignment = Alignment.Center
    ) {
        if (itemId != null) {
            Image(
                bitmap = MinecraftSprites.getSprite(itemId),
                contentDescription = itemId,
                filterQuality = FilterQuality.None,
                modifier = Modifier.size(36.dp)
            )

            // Clear dot
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(2.dp)
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(Color(0xDDE52626))
                        .clickable { onClear() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear slot",
                        tint = Color.White,
                        modifier = Modifier.size(10.dp)
                    )
                }
            }
        } else {
            Text(
                text = "${index + 1}",
                fontSize = 11.sp,
                color = if (isSelected || isHovered) MinecraftDiamond else Color(0x40FFFFFF),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * Large Apple Vision Liquid Glass Result Chamber with Elastic Stretch Pop.
 */
@Composable
private fun AppleGlassResultChamber(
    matchedRecipe: CraftingRecipe?,
    onClick: () -> Unit = {}
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = if (matchedRecipe != null) 1.08f else 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

    Box(
        modifier = Modifier
            .size(74.dp)
            .scale(pulseScale)
            .clip(RoundedCornerShape(18.dp))
            .background(
                if (matchedRecipe != null) {
                    Brush.radialGradient(
                        listOf(
                            Color(0x604AEDD9),
                            Color(0x35102525),
                            Color(0x80050A10)
                        )
                    )
                } else {
                    Brush.linearGradient(
                        listOf(
                            Color(0x22FFFFFF),
                            Color(0x10FFFFFF)
                        )
                    )
                }
            )
            .border(
                width = if (matchedRecipe != null) 2.dp else 1.25.dp,
                brush = if (matchedRecipe != null) {
                    Brush.linearGradient(
                        listOf(
                            Color(0xFFFFFFFF),
                            MinecraftDiamond,
                            MinecraftGreen,
                            Color(0x804AEDD9)
                        )
                    )
                } else {
                    Brush.linearGradient(
                        listOf(
                            Color(0x80FFFFFF),
                            Color(0x20FFFFFF)
                        )
                    )
                },
                shape = RoundedCornerShape(18.dp)
            )
            .drawWithContent {
                drawContent()
                val h = 1.5.dp.toPx()
                drawRoundRect(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Transparent,
                            Color.White.copy(alpha = 0.9f),
                            Color.Transparent
                        )
                    ),
                    topLeft = Offset(8.dp.toPx(), 0.5.dp.toPx()),
                    size = Size(size.width - 16.dp.toPx(), h),
                    cornerRadius = CornerRadius(h / 2, h / 2)
                )
            }
            .elasticGlassPress(
                pressScaleX = 0.92f,
                pressScaleY = 0.92f,
                onClick = onClick
            )
            .testTag("sandbox_result_slot"),
        contentAlignment = Alignment.Center
    ) {
        if (matchedRecipe != null) {
            Image(
                bitmap = MinecraftSprites.getSprite(matchedRecipe.id),
                contentDescription = matchedRecipe.name,
                filterQuality = FilterQuality.None,
                modifier = Modifier.size(48.dp)
            )

            // Output quantity pill
            if (matchedRecipe.outputCount > 1) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xEE1E1E1E))
                        .border(0.75.dp, Color(0x80FFFFFF), RoundedCornerShape(6.dp))
                        .padding(horizontal = 4.dp, vertical = 1.dp)
                ) {
                    Text(
                        text = "${matchedRecipe.outputCount}",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        } else {
            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = "Empty result",
                tint = Color(0x40FFFFFF),
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

/**
 * Apple Liquid Glass Palette Tile with tactile elastic rubber-band press and Drag-and-Drop initiation.
 */
@Composable
private fun AppleGlassPaletteItem(
    item: SandboxItem,
    onClick: () -> Unit,
    onStartDrag: (String, Int?, Offset) -> Unit,
    onUpdateDrag: (Offset) -> Unit,
    onEndDrag: () -> Unit,
    onCancelDrag: () -> Unit
) {
    var itemGlobalPos by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = Modifier
            .onGloballyPositioned { coordinates ->
                itemGlobalPos = coordinates.positionInRoot()
            }
            .clip(RoundedCornerShape(14.dp))
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0x22FFFFFF),
                        Color(0x0EFFFFFF)
                    )
                )
            )
            .border(
                1.dp,
                Brush.linearGradient(
                    listOf(
                        Color(0x80FFFFFF),
                        Color(0x18FFFFFF),
                        Color(0x254AEDD9)
                    )
                ),
                RoundedCornerShape(14.dp)
            )
            .pointerInput(item.id) {
                detectDragGesturesAfterLongPress(
                    onDragStart = { offset ->
                        onStartDrag(item.id, null, itemGlobalPos + offset)
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        onUpdateDrag(dragAmount)
                    },
                    onDragEnd = { onEndDrag() },
                    onDragCancel = { onCancelDrag() }
                )
            }
            .elasticGlassPress(
                pressScaleX = 0.88f,
                pressScaleY = 0.88f,
                onClick = onClick
            )
            .padding(6.dp)
            .testTag("palette_item_${item.id}"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0x18000000)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    bitmap = MinecraftSprites.getSprite(item.id),
                    contentDescription = item.name,
                    filterQuality = FilterQuality.None,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.name,
                fontSize = 10.sp,
                color = Color(0xFFE2E8F0),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

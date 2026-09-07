package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.audio.MinecraftAudioHaptics
import com.example.data.AppDatabase
import com.example.data.RecipeRepository
import com.example.model.RecipeCategory
import com.example.ui.components.AppTab
import com.example.ui.components.CraftingSandboxScreen
import com.example.ui.components.LiquidGlassBackground
import com.example.ui.components.LiquidGlassBadge
import com.example.ui.components.LiquidGlassPanel
import com.example.ui.components.LiquidGlassTabBar
import com.example.ui.components.MinecraftSearchBar
import com.example.ui.components.RecipeCard
import com.example.ui.components.RecipeDetailSheet
import com.example.ui.components.elasticGlassPress
import com.example.ui.sprite.MinecraftSprites
import com.example.ui.theme.MinecraftDiamond
import com.example.ui.theme.MinecraftGold
import com.example.ui.theme.MinecraftGreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.RecipeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MinecraftSprites.init(applicationContext)
        MinecraftAudioHaptics.initialize(applicationContext)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val context = LocalContext.current
                val database = AppDatabase.getDatabase(context)
                val repository = RecipeRepository(database.favoriteDao())
                val viewModel: RecipeViewModel = viewModel(
                    factory = RecipeViewModel.Factory(repository)
                )

                MinecraftCraftingApp(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinecraftCraftingApp(viewModel: RecipeViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val listState = rememberLazyListState()

    LiquidGlassBackground(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .testTag("app_top_bar")
                ) {
                    LiquidGlassPanel(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        shape = RoundedCornerShape(29.dp),
                        borderHighlightColor = Color(0xF0FFFFFF)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Milled Glass Lens Icon
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0x30FFFFFF))
                                        .border(1.dp, Color(0x66FFFFFF), RoundedCornerShape(12.dp))
                                        .padding(4.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        bitmap = MinecraftSprites.getSprite("crafting_table"),
                                        contentDescription = "Crafting Table",
                                        filterQuality = FilterQuality.None,
                                        modifier = Modifier.size(28.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "Crafting Guide",
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 17.sp,
                                            color = Color.White
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        LiquidGlassBadge(
                                            text = "130+",
                                            accentColor = MinecraftDiamond
                                        )
                                    }
                                    Text(
                                        text = "Apple Liquid Glass • Vision OS",
                                        fontSize = 10.5.sp,
                                        color = MinecraftDiamond,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }

                            // Vision OS Pill Tag
                            LiquidGlassBadge(
                                text = "STRETCH",
                                accentColor = MinecraftGreen
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Floating Liquid Glass Navigation Tab Bar (Recipe Book vs Crafting Sandbox)
                LiquidGlassTabBar(
                    selectedTab = uiState.currentTab,
                    onTabSelected = { viewModel.setTab(it) },
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                // Tab Content Switching
                when (uiState.currentTab) {
                    AppTab.SANDBOX -> {
                        CraftingSandboxScreen(
                            gridState = uiState.sandboxGrid,
                            onSlotSelected = { viewModel.selectSandboxSlot(it) },
                            selectedSlotIndex = uiState.selectedSlotIndex,
                            onSetSlotItem = { idx, item -> viewModel.setSandboxSlotItem(idx, item) },
                            onMoveOrSwapSlot = { from, to -> viewModel.moveOrSwapSandboxSlot(from, to) },
                            onClearGrid = { viewModel.clearSandboxGrid() },
                            onLoadRecipe = { viewModel.loadRecipeIntoSandbox(it) },
                            matchedRecipe = uiState.sandboxMatchedRecipe
                        )
                    }

                    AppTab.RECIPES -> {
                        // Search Bar and Category Chips
                        MinecraftSearchBar(
                            query = uiState.searchQuery,
                            onQueryChange = { viewModel.onSearchQueryChange(it) },
                            selectedCategory = uiState.selectedCategory,
                            onCategorySelected = { viewModel.onCategorySelect(it) },
                            favoriteCount = uiState.favoriteIds.size,
                            resultCount = uiState.filteredRecipes.size
                        )

                        // Recipe List or Empty State
                        if (uiState.filteredRecipes.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = if (uiState.selectedCategory == RecipeCategory.FAVORITES) Icons.Default.Star else Icons.Default.SearchOff,
                                        contentDescription = null,
                                        tint = if (uiState.selectedCategory == RecipeCategory.FAVORITES) MinecraftGold else Color(0x66FFFFFF),
                                        modifier = Modifier.size(64.dp)
                                    )

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Text(
                                        text = if (uiState.selectedCategory == RecipeCategory.FAVORITES) {
                                            "No Favorite Recipes Yet"
                                        } else {
                                            "No Recipes Found"
                                        },
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = Color.White
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = if (uiState.selectedCategory == RecipeCategory.FAVORITES) {
                                            "Tap the star icon on any recipe to quickly bookmark it for fast reference while playing."
                                        } else {
                                            "No crafting recipes matching \"${uiState.searchQuery}\". Try searching another item or ingredient name."
                                        },
                                        fontSize = 13.sp,
                                        color = Color(0xFFA5B2C9),
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                        lineHeight = 18.sp
                                    )

                                    if (uiState.searchQuery.isNotEmpty() || uiState.selectedCategory != RecipeCategory.ALL) {
                                        Spacer(modifier = Modifier.height(16.dp))
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(20.dp))
                                                .background(
                                                    androidx.compose.ui.graphics.Brush.horizontalGradient(
                                                        listOf(
                                                            Color(0xFF2E7D32),
                                                            Color(0xFF00897B)
                                                        )
                                                    )
                                                )
                                                .border(
                                                    1.dp,
                                                    Color(0xE0FFFFFF),
                                                    RoundedCornerShape(20.dp)
                                                )
                                                .elasticGlassPress(
                                                    pressScaleX = 0.90f,
                                                    pressScaleY = 0.90f,
                                                    onClick = {
                                                        viewModel.onSearchQueryChange("")
                                                        viewModel.onCategorySelect(RecipeCategory.ALL)
                                                    }
                                                )
                                                .padding(horizontal = 20.dp, vertical = 10.dp)
                                                .testTag("reset_filters_button"),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "Show All Recipes",
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 13.5.sp
                                            )
                                        }
                                    }
                                }
                            }
                        } else {
                            LazyColumn(
                                state = listState,
                                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .testTag("recipe_list")
                            ) {
                                items(
                                    items = uiState.filteredRecipes,
                                    key = { it.id }
                                ) { recipe ->
                                    RecipeCard(
                                        recipe = recipe,
                                        isFavorite = uiState.favoriteIds.contains(recipe.id),
                                        onToggleFavorite = { viewModel.toggleFavorite(recipe.id) },
                                        onClick = { viewModel.selectRecipe(recipe) }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Detail Bottom Sheet
            uiState.selectedRecipe?.let { selected ->
                RecipeDetailSheet(
                    recipe = selected,
                    isFavorite = uiState.favoriteIds.contains(selected.id),
                    onToggleFavorite = { viewModel.toggleFavorite(selected.id) },
                    onDismiss = { viewModel.selectRecipe(null) },
                    onNavigateToRecipe = { targetId ->
                        viewModel.selectRecipeById(targetId)
                    },
                    onOpenInSandbox = { recipeToTest ->
                        viewModel.loadRecipeIntoSandbox(recipeToTest)
                    },
                    sheetState = sheetState
                )
            }
        }
    }
}

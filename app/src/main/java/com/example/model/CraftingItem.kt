package com.example.model

enum class RecipeCategory(val displayName: String, val iconName: String) {
    ALL("All", "ic_all"),
    TOOLS("Tools", "ic_tools"),
    COMBAT("Combat", "ic_combat"),
    BUILDING("Building", "ic_building"),
    REDSTONE("Redstone", "ic_redstone"),
    FOOD("Food", "ic_food"),
    BREWING("Brewing", "ic_brewing"),
    TRANSPORT("Transport", "ic_transport"),
    FAVORITES("Favorites", "ic_fav")
}

data class IngredientCount(
    val itemId: String,
    val itemName: String,
    val count: Int
)

data class CraftingRecipe(
    val id: String,
    val name: String,
    val category: RecipeCategory,
    val description: String,
    val stackSize: Int = 64,
    val outputCount: Int = 1,
    val isShapeless: Boolean = false,
    // 9 slots (0..8). 0,1,2 is top row; 3,4,5 is middle row; 6,7,8 is bottom row
    val grid: List<String?> = List(9) { null },
    val ingredients: List<IngredientCount> = emptyList(),
    val statInfo: String? = null
)

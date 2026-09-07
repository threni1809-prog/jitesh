package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.RecipeRepository
import com.example.model.CraftingRecipe
import com.example.model.RecipeCategory
import com.example.model.RecipeDatabase
import com.example.ui.components.AppTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class RecipeUiState(
    val currentTab: AppTab = AppTab.RECIPES,
    val searchQuery: String = "",
    val selectedCategory: RecipeCategory = RecipeCategory.ALL,
    val favoriteIds: Set<String> = emptySet(),
    val filteredRecipes: List<CraftingRecipe> = emptyList(),
    val selectedRecipe: CraftingRecipe? = null,
    // Sandbox State
    val sandboxGrid: List<String?> = List(9) { null },
    val selectedSlotIndex: Int = 0,
    val sandboxMatchedRecipe: CraftingRecipe? = null
)

class RecipeViewModel(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _currentTab = MutableStateFlow(AppTab.RECIPES)
    private val _searchQuery = MutableStateFlow("")
    private val _selectedCategory = MutableStateFlow(RecipeCategory.ALL)
    private val _selectedRecipe = MutableStateFlow<CraftingRecipe?>(null)

    // Sandbox reactive state
    private val _sandboxGrid = MutableStateFlow<List<String?>>(List(9) { null })
    private val _selectedSlotIndex = MutableStateFlow(0)
    private val _sandboxMatchedRecipe = MutableStateFlow<CraftingRecipe?>(null)

    val favoriteIds: StateFlow<Set<String>> = repository.favoriteIds
        .combine(MutableStateFlow(Unit)) { list, _ -> list.toSet() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    val uiState: StateFlow<RecipeUiState> = combine(
        combine(_currentTab, _searchQuery, _selectedCategory, favoriteIds) { tab, query, cat, favs ->
            Quadruple(tab, query, cat, favs)
        },
        combine(_selectedRecipe, _sandboxGrid, _selectedSlotIndex, _sandboxMatchedRecipe) { sel, grid, slotIdx, matched ->
            Quadruple(sel, grid, slotIdx, matched)
        }
    ) { (tab, query, category, favs), (selected, grid, slotIdx, matched) ->
        val trimmedQuery = query.trim().lowercase()

        val filtered = RecipeDatabase.RECIPES.filter { recipe ->
            // Category check
            val matchesCategory = when (category) {
                RecipeCategory.ALL -> true
                RecipeCategory.FAVORITES -> favs.contains(recipe.id)
                else -> recipe.category == category
            }

            // Query check (matches recipe name, id, category, or ingredient names)
            val matchesQuery = if (trimmedQuery.isEmpty()) {
                true
            } else {
                recipe.name.lowercase().contains(trimmedQuery) ||
                recipe.id.lowercase().contains(trimmedQuery) ||
                recipe.description.lowercase().contains(trimmedQuery) ||
                recipe.ingredients.any { it.itemName.lowercase().contains(trimmedQuery) || it.itemId.lowercase().contains(trimmedQuery) }
            }

            matchesCategory && matchesQuery
        }

        RecipeUiState(
            currentTab = tab,
            searchQuery = query,
            selectedCategory = category,
            favoriteIds = favs,
            filteredRecipes = filtered,
            selectedRecipe = selected,
            sandboxGrid = grid,
            selectedSlotIndex = slotIdx,
            sandboxMatchedRecipe = matched
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = RecipeUiState(
            filteredRecipes = RecipeDatabase.RECIPES
        )
    )

    fun setTab(tab: AppTab) {
        _currentTab.value = tab
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onCategorySelect(category: RecipeCategory) {
        _selectedCategory.value = category
    }

    fun selectRecipe(recipe: CraftingRecipe?) {
        _selectedRecipe.value = recipe
    }

    fun selectRecipeById(recipeId: String) {
        val recipe = RecipeDatabase.findById(recipeId)
        if (recipe != null) {
            _selectedRecipe.value = recipe
        }
    }

    fun toggleFavorite(recipeId: String) {
        viewModelScope.launch {
            val isFav = favoriteIds.value.contains(recipeId)
            repository.toggleFavorite(recipeId, isFav)
        }
    }

    // --- Sandbox Actions ---

    fun selectSandboxSlot(index: Int) {
        if (index in 0..8) {
            _selectedSlotIndex.value = index
        }
    }

    fun setSandboxSlotItem(index: Int, itemId: String?) {
        if (index in 0..8) {
            val newGrid = _sandboxGrid.value.toMutableList()
            newGrid[index] = itemId
            _sandboxGrid.value = newGrid
            _sandboxMatchedRecipe.value = RecipeDatabase.matchRecipe(newGrid)
        }
    }

    fun moveOrSwapSandboxSlot(fromIndex: Int, toIndex: Int) {
        if (fromIndex in 0..8 && toIndex in 0..8 && fromIndex != toIndex) {
            val newGrid = _sandboxGrid.value.toMutableList()
            val temp = newGrid[toIndex]
            newGrid[toIndex] = newGrid[fromIndex]
            newGrid[fromIndex] = temp
            _sandboxGrid.value = newGrid
            _sandboxMatchedRecipe.value = RecipeDatabase.matchRecipe(newGrid)
        }
    }

    fun clearSandboxGrid() {
        val newGrid = List(9) { null }
        _sandboxGrid.value = newGrid
        _selectedSlotIndex.value = 0
        _sandboxMatchedRecipe.value = null
    }

    fun loadRecipeIntoSandbox(recipe: CraftingRecipe) {
        val newGrid = recipe.grid.map { it }
        _sandboxGrid.value = newGrid
        _sandboxMatchedRecipe.value = recipe
        _currentTab.value = AppTab.SANDBOX
    }

    private data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)

    class Factory(private val repository: RecipeRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RecipeViewModel(repository) as T
        }
    }
}

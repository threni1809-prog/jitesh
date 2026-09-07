package com.example.data

import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val favoriteDao: FavoriteDao) {
    val favoriteIds: Flow<List<String>> = favoriteDao.getAllFavoriteIds()

    suspend fun toggleFavorite(recipeId: String, isFav: Boolean) {
        if (isFav) {
            favoriteDao.deleteFavorite(recipeId)
        } else {
            favoriteDao.insertFavorite(FavoriteEntity(recipeId = recipeId))
        }
    }

    suspend fun isFavorite(recipeId: String): Boolean {
        return favoriteDao.isFavorite(recipeId)
    }
}

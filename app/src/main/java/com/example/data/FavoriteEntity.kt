package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val recipeId: String,
    val timestamp: Long = System.currentTimeMillis()
)

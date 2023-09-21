package com.example.foodstand.data.repository.local

import com.example.foodstand.data.database.database.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun insertRecipes(recipeEntity: RecipeEntity)

    fun getRecipes(): Flow<List<RecipeEntity>>
}
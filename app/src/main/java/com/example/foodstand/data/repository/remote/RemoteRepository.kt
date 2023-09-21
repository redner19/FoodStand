package com.example.foodstand.data.repository.remote

import com.example.foodstand.data.model.foodRecipeModel.FoodRecipeModel

interface RemoteRepository {
    suspend fun getRecipe(queries: Map<String, String>) : FoodRecipeModel
}
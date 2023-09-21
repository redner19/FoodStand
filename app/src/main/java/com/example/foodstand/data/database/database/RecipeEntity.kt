package com.example.foodstand.data.database.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodstand.data.model.foodRecipeModel.FoodRecipeModel
import com.example.foodstand.util.Constants.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipeEntity(
    var foodRecipe: FoodRecipeModel
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}
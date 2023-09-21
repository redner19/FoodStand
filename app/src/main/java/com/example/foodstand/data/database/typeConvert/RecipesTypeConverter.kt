package com.example.foodstand.data.database.typeConvert

import androidx.room.TypeConverter
import com.example.foodstand.data.model.foodRecipeModel.FoodRecipeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(recipeModel: FoodRecipeModel): String =
        gson.toJson(recipeModel)

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipeModel =
        gson.fromJson(data, object : TypeToken<FoodRecipeModel>(){}.type)
}
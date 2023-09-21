package com.example.foodstand.util

object Constants {
    const val BASE_URL = "https://api.spoonacular.com"

    // API Query Keys
    const val QUERY_ITEM_SIZE = "number"
    const val QUERY_API_KEY = "apiKey"
    const val QUERY_TYPE = "type"
    const val QUERY_DIET = "diet"
    const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
    const val QUERY_FILL_INGREDIENTS = "fillIngredients"

    // ROOM Database
    const val RECIPES_NAME = "recipes_database"
    const val RECIPES_TABLE = "recipes_table"

    // Bottom Sheets Defaults and Preferences
    const val DEFAULT_RECIPES_NUMBER = "25"
    const val DEFAULT_MEAL_TYPE = "main course"
    const val DEFAULT_DIET_TYPE = "gluten free"

    const val PREFERENCES_NAME = "FoodStand_Preferences"
    const val PREFERENCES_MEAL_TYPE = "mealType"
    const val PREFERENCES_MEAL_TYPE_ID = "mealTypeId"
    const val PREFERENCES_DIET_TYPE = "dietType"
    const val PREFERENCES_DIET_TYPE_ID = "dietTypeId"
}


//TODO
//Phase 1 - 60% done
//Implement the skeleton of the UI - Partially done
//Implement the Models/DTOs (Food details, Food list, food joke) - Partially done
//Implement the DI (Dependency Injection) - Partially done
//Implement the Repositories - Partially done
//Call the first implement API - Done

//TODO
//Phase 2 - 0% done
//Implement the functionality of the phase 1
//Implement search functionality
//Implement the skeleton of the food recipe details
//Implement the skeleton of the food jokes
//Continue implementing the Repository/Services/DI

//TODO
//Phase 3 - 0% done
//Implement the app's icon
//Implement offline caching
//Polish the app
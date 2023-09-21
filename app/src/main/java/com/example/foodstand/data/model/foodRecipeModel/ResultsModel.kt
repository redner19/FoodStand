package com.example.foodstand.data.model.foodRecipeModel

data class ResultsModel(
    val AggregateLikes: Int,
    val Cheap: Boolean,
    val DairyFree: Boolean,
    val ExtendedIngredients: List<ExtendedIngredientModel>,
    val Gaps: String,
    val GlutenFree: Boolean,
    val HealthScore: Int,
    val Id: Int,
    val Image: String,
    val ReadyInMinutes: Int,
    val SourceName: String,
    val SourceUrl: String,
    val Summary: String,
    val Title: String,
    val Vegan: Boolean,
    val Vegetarian: Boolean,
    val VeryHealthy: Boolean
)
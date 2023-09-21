package com.example.foodstand.data.dto.response.foodRecipeDto

data class ExtendedIngredientDto(
    val amount: Double,
    val consistency: String,
    val image: String,
    val name: String,
    val original: String,
    val unit: String
)
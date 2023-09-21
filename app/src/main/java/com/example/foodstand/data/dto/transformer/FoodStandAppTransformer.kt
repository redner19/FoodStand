package com.example.foodstand.data.dto.transformer

import com.example.foodstand.data.dto.response.foodRecipeDto.ExtendedIngredientDto
import com.example.foodstand.data.dto.response.foodRecipeDto.FoodRecipeDto
import com.example.foodstand.data.dto.response.foodRecipeDto.ResultDto
import com.example.foodstand.data.model.foodRecipeModel.ExtendedIngredientModel
import com.example.foodstand.data.model.foodRecipeModel.FoodRecipeModel
import com.example.foodstand.data.model.foodRecipeModel.ResultsModel

fun ExtendedIngredientDto.transform() =
    ExtendedIngredientModel(
        Amount = amount,
        Consistency = consistency,
        Image = image,
        Name = name,
        Original = original,
        Unit = unit
    )

fun ResultDto.transform() =
    ResultsModel(
        AggregateLikes = aggregateLikes,
        Cheap = cheap,
        DairyFree = dairyFree,
        ExtendedIngredients = extendedIngredients.map { it.transform() },
        Gaps = gaps,
        GlutenFree = glutenFree,
        HealthScore = healthScore,
        Id = id,
        Image = image,
        ReadyInMinutes = readyInMinutes,
        SourceName = sourceName,
        SourceUrl = sourceUrl,
        Summary = summary,
        Title = title,
        Vegan = vegan,
        Vegetarian = vegetarian,
        VeryHealthy = veryHealthy
    )

fun FoodRecipeDto.transform() =
    FoodRecipeModel(
        Results = results.map { it.transform() }
    )
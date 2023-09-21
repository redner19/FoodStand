package com.example.foodstand.data.service

import com.example.foodstand.data.dto.response.foodRecipeDto.FoodRecipeDto
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodStandService {

    @GET("/recipes/complexSearch")
    suspend fun getRecipe(
        @QueryMap queryMap: Map<String, String>
    ): FoodRecipeDto

}
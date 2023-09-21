package com.example.foodstand.data.repository.remote

import com.example.foodstand.data.dto.transformer.transform
import com.example.foodstand.data.model.foodRecipeModel.FoodRecipeModel
import com.example.foodstand.data.service.FoodStandService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RemoteRepositoryImpl @Inject constructor(
    private val service : FoodStandService
    ) : RemoteRepository {

    override suspend fun getRecipe(queries: Map<String, String>): FoodRecipeModel =
        service.getRecipe(queries).transform()
}
package com.example.foodstand.data.repository.local

import com.example.foodstand.data.database.database.RecipeEntity
import com.example.foodstand.data.database.database.RecipesDao
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class LocalRepositoryImpl @Inject constructor(
    private val recipesDao: RecipesDao
): LocalRepository {

    override suspend fun insertRecipes(recipeEntity: RecipeEntity) =
        recipesDao.insertRecipes(recipeEntity)

    override fun getRecipes(): Flow<List<RecipeEntity>> =
        recipesDao.getRecipes()

}
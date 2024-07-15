package com.example.foodstand.views.fragments.recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodstand.BuildConfig
import com.example.foodstand.data.database.dataStore.DataStoreRepository
import com.example.foodstand.data.database.database.RecipeEntity
import com.example.foodstand.data.model.foodRecipeModel.FoodRecipeModel
import com.example.foodstand.data.model.foodRecipeModel.ResultsModel
import com.example.foodstand.data.repository.local.LocalRepository
import com.example.foodstand.data.repository.remote.RemoteRepository
import com.example.foodstand.util.Constants.DEFAULT_DIET_TYPE
import com.example.foodstand.util.Constants.DEFAULT_MEAL_TYPE
import com.example.foodstand.util.Constants.DEFAULT_RECIPES_NUMBER
import com.example.foodstand.util.Constants.QUERY_ADD_RECIPE_INFORMATION
import com.example.foodstand.util.Constants.QUERY_API_KEY
import com.example.foodstand.util.Constants.QUERY_DIET
import com.example.foodstand.util.Constants.QUERY_FILL_INGREDIENTS
import com.example.foodstand.util.Constants.QUERY_ITEM_SIZE
import com.example.foodstand.util.Constants.QUERY_TYPE
import com.example.foodstand.views.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val local: LocalRepository,
    private val remote: RemoteRepository,
    private val dataStoreRepository: DataStoreRepository
): BaseViewModel() {

    /**Local**/
    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    val localFoodRecipesListLiveData: LiveData<List<RecipeEntity>> =
        local.getRecipes().asLiveData()

    val readMealAndDietType = dataStoreRepository.readMealAndDietType

    /**Remote**/
    private val _foodRecipesListLiveData = MutableLiveData<List<ResultsModel>>()

    val foodRecipesListLiveData: LiveData<List<ResultsModel>> =
        _foodRecipesListLiveData

    init {
        viewModelScope.launch {
            readMealAndDietType.collect{ value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }
    }

    fun loadFoodRecipes(){
        launchRequestWithErrorHandling {
            Log.d("FoodRecipe","remote database called from VM!")
            val result = remote.getRecipe(applyQueries())
            _foodRecipesListLiveData.value = result.Results
            offlineCacheRecipe(result)
        }
    }

    fun saveMealAndDietType(mealType: String, mealTypeId: Int, dietType: String, dietTypeId: Int) =
        viewModelScope.launch(Dispatchers.IO){
            dataStoreRepository.saveMealAndDietType(mealType, mealTypeId, dietType, dietTypeId)
        }

    private fun insertOfflineCacheRecipe(recipeEntity: RecipeEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            local.insertRecipes(recipeEntity)
        }

    private fun offlineCacheRecipe(recipeModel: FoodRecipeModel) =
        insertOfflineCacheRecipe(RecipeEntity(recipeModel))

    private fun applyQueries(): HashMap<String, String> {
        val queries : HashMap<String ,String> = HashMap()

        queries[QUERY_ITEM_SIZE] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = BuildConfig.SPOON_API
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }
}
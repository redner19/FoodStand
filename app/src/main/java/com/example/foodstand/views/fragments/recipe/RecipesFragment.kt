package com.example.foodstand.views.fragments.recipe

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodstand.R
import com.example.foodstand.databinding.FragmentRecipesBinding
import com.example.foodstand.util.FragmentExt.observeOnce
import com.example.foodstand.util.FragmentExt.viewLifecycle
import com.example.foodstand.views.base.BaseFragment
import com.example.foodstand.views.fragments.recipe.adapter.OnClickListener
import com.example.foodstand.views.fragments.recipe.adapter.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : BaseFragment(R.layout.fragment_recipes), OnClickListener, SearchView.OnQueryTextListener {
    override val viewModel by viewModels<RecipesViewModel>()
    private val args by navArgs<RecipesFragmentArgs>()
    private val mAdapter by lazy { RecipesAdapter(this) }
    private var binding by viewLifecycle<FragmentRecipesBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecipesBinding.bind(view)

        with(binding){
            recyclerView.adapter = mAdapter
            swipeToRefresh.setOnRefreshListener {
                val results = viewModel.localFoodRecipesListLiveData.value
                if(results!!.isNotEmpty() && isNetworkAvailable){
                    Log.d("FoodRecipe","local database called from Fragment 0")
                    binding.swipeToRefresh.isRefreshing = false
                    mAdapter.submitList(results[0].foodRecipe.Results)
                }else{
                    viewModel.loadFoodRecipes()
                }
            }

            recipesFab.setOnClickListener {
                if(isNetworkAvailable){
                    findNavController().navigate(R.id.action_recipesFragment_to_bottomSheetFragment)
                }
            }
        }

        with(viewModel){
            foodRecipesListLiveData.observe(viewLifecycleOwner) {responseList ->
                binding.swipeToRefresh.isRefreshing = false
                mAdapter.submitList(responseList)
            }

            lifecycleScope.launch {
                localFoodRecipesListLiveData.observeOnce(viewLifecycleOwner) { database ->
                    if(database.isNotEmpty() && !args.backFromBottomSheet){
                        Log.d("FoodRecipe","local database called from Fragment! 1")
                        binding.swipeToRefresh.isRefreshing = false
                        mAdapter.submitList(database[0].foodRecipe.Results)
                    } else {
                        loadFoodRecipes()
                    }
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipe_menu, menu)

        val search = menu.findItem(R.id.app_bar_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }


    override fun onItemClicked(id: Int) {
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
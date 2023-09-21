package com.example.foodstand.views.fragments.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.foodstand.R
import com.example.foodstand.databinding.FragmentFavoriteRecipesBinding
import com.example.foodstand.util.FragmentExt.viewLifecycle
import com.example.foodstand.views.base.BaseFragment
import com.example.foodstand.views.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class FavoriteRecipesFragment : BaseFragment(R.layout.fragment_favorite_recipes) {
    override val viewModel by viewModels<FavoriteRecipesViewModel>()
    private var binding by viewLifecycle<FragmentFavoriteRecipesBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteRecipesBinding.bind(view)

        with(binding){
            textId.text = Random.nextInt(1, 10).toString()
        }
    }
}
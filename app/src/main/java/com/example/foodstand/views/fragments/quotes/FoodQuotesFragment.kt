package com.example.foodstand.views.fragments.quotes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.foodstand.R
import com.example.foodstand.databinding.FragmentFoodQuotesBinding
import com.example.foodstand.util.FragmentExt.viewLifecycle
import com.example.foodstand.views.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodQuotesFragment : BaseFragment(R.layout.fragment_food_quotes) {
    override val viewModel by viewModels<FoodQuotesViewModel>()
    private var binding by viewLifecycle<FragmentFoodQuotesBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFoodQuotesBinding.bind(view)

        with(binding){

        }
    }
}
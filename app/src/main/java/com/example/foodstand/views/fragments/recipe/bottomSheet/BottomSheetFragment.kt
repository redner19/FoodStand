package com.example.foodstand.views.fragments.recipe.bottomSheet

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.foodstand.R
import com.example.foodstand.databinding.BottomSheetBinding
import com.example.foodstand.util.Constants.DEFAULT_DIET_TYPE
import com.example.foodstand.util.Constants.DEFAULT_MEAL_TYPE
import com.example.foodstand.util.FragmentExt.viewLifecycle
import com.example.foodstand.views.fragments.recipe.RecipesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.Locale

class BottomSheetFragment : BottomSheetDialogFragment(R.layout.bottom_sheet) {

    private lateinit var viewModel: RecipesViewModel

    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0

    private var binding by viewLifecycle<BottomSheetBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = BottomSheetBinding.bind(view)
        with(viewModel){
            readMealAndDietType.asLiveData().observe(viewLifecycleOwner) { value ->
                mealTypeChip = value.selectedMealType
                dietTypeChip = value.selectedDietType

                updateChip(value.selectedMealTypeId, binding.mealTypeChipGroup)
                updateChip(value.selectedDietTypeId, binding.dietTypeChipGroup)

            }
        }
        with(binding){
            mealTypeChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                mealTypeChipId = group.checkedChipId
                mealTypeChip = group
                    .findViewById<Chip>(mealTypeChipId)
                    .text
                    .toString()
                    .lowercase()
            }
            dietTypeChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                dietTypeChipId = group.checkedChipId
                dietTypeChip = group
                    .findViewById<Chip>(dietTypeChipId)
                    .text
                    .toString()
                    .lowercase()
            }

            applyBtn.setOnClickListener {
                viewModel.saveMealAndDietType(
                    mealTypeChip,
                    mealTypeChipId,
                    dietTypeChip,
                    dietTypeChipId
                )

                val action =
                    BottomSheetFragmentDirections.actionBottomSheetFragmentToRecipesFragment(true)
                findNavController().navigate(action)
            }
        }
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if(chipId != 0){
            try
            {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            }catch (e: Exception){
                Log.d("BottomSheetFragment",e.message.toString())
            }
        }
    }
}
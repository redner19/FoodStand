package com.example.foodstand.views.fragments.favorites

import com.example.foodstand.data.repository.remote.RemoteRepository
import com.example.foodstand.views.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteRecipesViewModel @Inject constructor(
    private val repository: RemoteRepository
) : BaseViewModel() {


}
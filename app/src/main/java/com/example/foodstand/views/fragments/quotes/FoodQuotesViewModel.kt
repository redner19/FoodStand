package com.example.foodstand.views.fragments.quotes

import com.example.foodstand.data.repository.remote.RemoteRepository
import com.example.foodstand.views.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodQuotesViewModel @Inject constructor(
    private val repository: RemoteRepository
) : BaseViewModel() {
}
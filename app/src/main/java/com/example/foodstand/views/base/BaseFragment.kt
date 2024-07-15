package com.example.foodstand.views.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.foodstand.R
import com.example.foodstand.util.NetworkListener
import kotlinx.coroutines.launch

abstract class BaseFragment(fragment: Int): Fragment(fragment) {
    protected abstract val viewModel : BaseViewModel
    protected var isNetworkAvailable = false
    private val networkListener: NetworkListener by lazy { NetworkListener() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.showErrorLiveData.observe(viewLifecycleOwner){ error ->
            Toast.makeText(requireContext(), getText(error), Toast.LENGTH_LONG).show()
        }

        lifecycleScope.launch {
            networkListener.checkNetworkAvailability(requireContext()).collect { network ->
                if(isNetworkAvailable != network)
                {
                    Toast.makeText(requireContext(),
                        getText(
                            if(network)
                                R.string.has_connection
                            else
                                R.string.no_connection),
                        Toast.LENGTH_LONG).show()

                    isNetworkAvailable = network
                }
            }
        }
    }
}
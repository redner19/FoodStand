package com.example.foodstand.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkListener : ConnectivityManager.NetworkCallback() {
    private val isNetworkAvailable = MutableStateFlow(false)

    fun checkNetworkAvailability(context: Context): MutableStateFlow<Boolean> {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.registerDefaultNetworkCallback(this)

        // Check initial network state (API 33 and above)
        isNetworkAvailable.value =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            connectivityManager.activeNetwork != null &&
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                        ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
            else
            // Fallback for older API levels (deprecated in API 33)
            connectivityManager.allNetworks.any { network ->
                connectivityManager.getNetworkCapabilities(network)?.hasCapability(
                    NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
            }

        return isNetworkAvailable
    }

    override fun onAvailable(network: Network) {
        isNetworkAvailable.value = true
    }
    override fun onLost(network: Network) {
        isNetworkAvailable.value = false
    }
}
package com.sardordev.burgerapp.screens.mainscreen

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

class InternerConnectionCheck(private val context: Context) : LiveData<Boolean>() {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            postValue(isInternetAvaiable())
        }

        override fun onLost(network: Network) {
            postValue(false)
        }


    }


    private val networkRequest =
        NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()

    override fun onActive() {
        super.onActive()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }


    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }


    private fun isInternetAvaiable(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork ?: return false

        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false


        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

    }

}
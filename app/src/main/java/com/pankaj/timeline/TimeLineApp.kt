package com.pankaj.timeline

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pankaj.pankaj.util.printInfoLog
import com.pankaj.timeline.di.mainModule
import com.pankaj.timeline.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TimeLineApp : Application() {
    companion object {
        private val _isConnectToNetwork = MutableLiveData<Boolean>()
        val isConnectToNetwork: LiveData<Boolean> = _isConnectToNetwork

        @Transient
        var isConnectNetwork: Boolean = false
    }

    internal var connectivityManager: ConnectivityManager? = null
    override fun onCreate() {
        super.onCreate()
        printInfoLog(this, "started application")
        startKoin {
            // Android context
            androidContext(this@TimeLineApp)
            // modules
            modules(listOf(mainModule, networkModule))
        }
        registerConnectivityManager(this)
    }


    internal fun registerConnectivityManager(context: Context) {
        connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()

        connectivityManager?.registerNetworkCallback(networkRequest.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    printInfoLog(this, "network---> lost $network")
                    _isConnectToNetwork.postValue(false)
                    isConnectNetwork = false
                    super.onLost(network)
                }

                override fun onAvailable(network: Network) {
                    _isConnectToNetwork.postValue(true)
                    isConnectNetwork = true
                    printInfoLog(this, "network---> onavailable $network")
                    super.onAvailable(network)
                }
            })
    }
}
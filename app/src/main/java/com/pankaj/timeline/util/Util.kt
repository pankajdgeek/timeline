package com.pankaj.pankaj.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.util.Log

fun <T> printInfoLog(clazz: T, log: Any?) {
    if (clazz is String)
        infoLog(clazz, log ?: "value is null")
    else
        infoLog((clazz as Object).javaClass.name, log ?: "value is null")
}


private fun infoLog(tag: String, log: Any) {
    Log.d(tag, log.toString())
}


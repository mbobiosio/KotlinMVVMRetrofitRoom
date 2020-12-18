package com.mbobiosio.countries.interfaces

interface NetworkCallback {
    fun onNetworkSuccess()
    fun onNetworkFailure(th: Throwable)
}
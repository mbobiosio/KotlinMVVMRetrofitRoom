package com.mbobiosio.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbobiosio.countries.App
import com.mbobiosio.countries.interfaces.NetworkCallback
import com.mbobiosio.countries.model.Country
import com.mbobiosio.countries.repo.CountryRepository
import com.mbobiosio.countries.util.isOnline

class CountryViewModel : ViewModel() {

    private var dataList: MutableLiveData<List<Country>> = MutableLiveData<List<Country>>()
        .apply {
            value = emptyList()
        }

    val progressBar = MutableLiveData(true)
    private val networkError: MutableLiveData<Boolean> = MutableLiveData()
    val apiError = MutableLiveData<String>()

    private var repository = CountryRepository.getRepository()

    fun fetchData(reload: Boolean): MutableLiveData<List<Country>> {

        if (isOnline(App.appContext())) {
            progressBar.value = true
            dataList = repository.getCountries(object : NetworkCallback {
                override fun onNetworkSuccess() {
                    progressBar.value = false
                }

                override fun onNetworkFailure(th: Throwable) {
                    apiError.value = th.message
                }
            }, reload)
        } else {
            networkError.value = true
        }
        return dataList
    }

    fun onRefreshData() {
        fetchData(true)
    }

}
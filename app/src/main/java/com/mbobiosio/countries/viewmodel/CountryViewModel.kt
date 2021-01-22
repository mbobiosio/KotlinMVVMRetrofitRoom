package com.mbobiosio.countries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbobiosio.countries.App
import com.mbobiosio.countries.interfaces.NetworkCallback
import com.mbobiosio.countries.model.domain.CountryDomainModel
import com.mbobiosio.countries.repo.CountryRepository
import com.mbobiosio.countries.util.NetworkUtil.isOnline
import timber.log.Timber

class CountryViewModel : ViewModel() {

    private var repository = CountryRepository.getRepository()

    private var dataList = repository.countries

    val progressBar = MutableLiveData(true)
    val networkError: MutableLiveData<Boolean> = MutableLiveData()
    val apiError = MutableLiveData<String>()

    fun fetchData(reload: Boolean): LiveData<List<CountryDomainModel>> {
        if (isOnline(App.appContext())) {
            repository.refreshDatabase(object : NetworkCallback {
                override fun onNetworkSuccess() {
                    progressBar.value = false
                    Timber.d("Successful")
                }

                override fun onNetworkFailure(th: Throwable) {
                    if (dataList.value.isNullOrEmpty()) apiError.value = th.message
                    progressBar.value = false
                    Timber.d("$th")
                }
            }, reload)
        } else {
            if (dataList.value.isNullOrEmpty()) networkError.value = true
            progressBar.value = false
            Timber.d("Network error")
        }
        return dataList
    }

}
package com.mbobiosio.countries.repo

import androidx.lifecycle.MutableLiveData
import com.mbobiosio.countries.App
import com.mbobiosio.countries.api.RetrofitClient
import com.mbobiosio.countries.database.AppDatabase
import com.mbobiosio.countries.interfaces.NetworkCallback
import com.mbobiosio.countries.model.Country
import com.mbobiosio.countries.model.dbmodel.CountryDbModel
import com.mbobiosio.countries.util.convertToDbModel
import com.mbobiosio.countries.util.convertToNetworkModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryRepository private constructor() {

    private val countryDao = AppDatabase.getInstance(App.appContext()).countryDao
    private lateinit var networkCallBack: NetworkCallback

    @Volatile
    var hasPreloadedFromDb: MutableLiveData<Boolean> = MutableLiveData()

    @Volatile
    private var countriesList: MutableLiveData<List<Country>> = MutableLiveData<List<Country>>()
        .apply {
            value = emptyList()
        }

    companion object {

        private var repository: CountryRepository? = null

        fun getRepository(): CountryRepository {
            if (repository == null) {
                synchronized(this) {
                    repository = CountryRepository()
                }
            }
            return repository!!
        }
    }

    fun loadFromDb(): MutableLiveData<List<Country>> {
        CoroutineScope(IO).launch {
            synchronized(this) {
                if (!getCountriesFromDb().isNullOrEmpty()) {
                    hasPreloadedFromDb.postValue(true)
                    countriesList.postValue(getCountriesFromDb())
                } else {
                    hasPreloadedFromDb.postValue(false)
                }
            }
        }

        return countriesList
    }


    private lateinit var mNetworkRequest: Call<List<Country>>

    fun getCountries(callback: NetworkCallback, refresh: Boolean): MutableLiveData<List<Country>> {

        networkCallBack = callback

        if (countriesList.value!!.isNotEmpty() && !refresh) {
            networkCallBack.onNetworkSuccess()
            return countriesList
        }

        mNetworkRequest = RetrofitClient.getRetrofit().getAPIService().getCountries()

        mNetworkRequest.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                CoroutineScope(IO).launch {
                    insertCountriesInDb(convertToDbModel(response.body()!!))
                    countriesList.postValue(getCountriesFromDb())
                }
                networkCallBack.onNetworkSuccess()
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                countriesList.value = emptyList()
                networkCallBack.onNetworkFailure(t)
            }
        })
/*

        mNetworkRequest.observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Country>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: List<Country>) {
                    countries.value = t
                    networkCallBack.onNetworkSuccess()
                }

                override fun onError(e: Throwable) {
                    countries.value = emptyList()
                    networkCallBack.onNetworkFailure(e)
                }

                override fun onComplete() {

                }
            })

*/
        return countriesList
    }

    private fun insertCountriesInDb(countries: List<CountryDbModel>) {
        if (countries.isNotEmpty()) countryDao.insertCountries(countries)
    }

    private fun getCountriesFromDb(): List<Country> =
        convertToNetworkModel(countryDao.getCountries())
}
package com.mbobiosio.countries.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.mbobiosio.countries.App
import com.mbobiosio.countries.api.RetrofitClient
import com.mbobiosio.countries.database.AppDatabase
import com.mbobiosio.countries.interfaces.NetworkCallback
import com.mbobiosio.countries.model.Country
import com.mbobiosio.countries.model.dbmodel.CountryDbModel
import com.mbobiosio.countries.model.domain.CountryDomainModel
import com.mbobiosio.countries.util.NetworkUtil.isOnline
import com.mbobiosio.countries.util.convertToDbModel
import com.mbobiosio.countries.util.convertToDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryRepository private constructor() {

    private val countryDao = AppDatabase.getInstance(App.appContext()).countryDao
    private lateinit var networkCallBack: NetworkCallback
    val countries: LiveData<List<CountryDomainModel>> =
        Transformations.map(countryDao.getCountries()) {
            it.convertToDomainModel()
        }

    private lateinit var mNetworkRequest: Call<List<Country>>

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

    fun refreshDatabase(callback: NetworkCallback, refresh: Boolean) {
        networkCallBack = callback
        mNetworkRequest = RetrofitClient.getRetrofit().getAPIService().getCountries()

        mNetworkRequest.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                CoroutineScope(IO).launch {
                    insertCountriesInDb(response.body()!!.convertToDbModel())
                }
                networkCallBack.onNetworkSuccess()
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                networkCallBack.onNetworkFailure(t)
            }
        })
    }

    private fun insertCountriesInDb(countries: List<CountryDbModel>) {
        if (countries.isNotEmpty()) countryDao.insertCountries(countries)
    }

    /*mNetworkRequest.observeOn(Schedulers.io())
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

    return countriesList*/
}
package com.mbobiosio.countries.repo

import androidx.lifecycle.MutableLiveData
import com.mbobiosio.countries.api.RetrofitClient
import com.mbobiosio.countries.interfaces.NetworkCallback
import com.mbobiosio.countries.model.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryRepository private constructor() {

    private lateinit var networkCallBack : NetworkCallback
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

    private lateinit var mNetworkRequest: Call<List<Country>>

    fun getCountries(callback: NetworkCallback, refresh: Boolean) : MutableLiveData<List<Country>> {

        networkCallBack = callback

        if (countriesList.value!!.isNotEmpty()  && !refresh) {
            networkCallBack.onNetworkSuccess()
            return countriesList
        }

        mNetworkRequest = RetrofitClient.getRetrofit().getAPIService().getCountries()

        mNetworkRequest.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                countriesList.value = response.body()
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

}
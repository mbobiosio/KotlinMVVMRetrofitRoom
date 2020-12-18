package com.mbobiosio.countries.api

import com.mbobiosio.countries.model.Country
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("all")
    fun getCountries() : Call<List<Country>>

}
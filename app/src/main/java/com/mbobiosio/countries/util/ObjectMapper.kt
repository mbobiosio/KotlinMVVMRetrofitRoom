package com.mbobiosio.countries.util

import com.mbobiosio.countries.model.Country
import com.mbobiosio.countries.model.dbmodel.CountryDbModel

fun convertToDbModel(countries: List<Country>) : List<CountryDbModel> {
    val countryDbModelList: ArrayList<CountryDbModel> = ArrayList()
    for (country in countries) {
        country.apply {
            countryDbModelList.add(CountryDbModel(name = name!!, capital = capital, region = region, subregion = subregion,
            population = population, demonym = demonym, timezones = timezones, alpha2code = alpha2code,
            flag = flag, callingCodes = callingCodes, currencies = currencies))
        }
    }
    return countryDbModelList
}

fun convertToNetworkModel(countries: List<CountryDbModel>) : List<Country> {
    val countryList: ArrayList<Country> = ArrayList()
    for (country in countries) {
        country.apply {
            countryList.add(Country(name = name, capital = capital, region = region, subregion = subregion,
                population = population, demonym = demonym, timezones = timezones, alpha2code = alpha2code,
                flag = flag, callingCodes = callingCodes, currencies = currencies))
        }
    }
    return countryList
}
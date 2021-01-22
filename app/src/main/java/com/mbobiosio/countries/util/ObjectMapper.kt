package com.mbobiosio.countries.util

import com.mbobiosio.countries.model.Country
import com.mbobiosio.countries.model.dbmodel.CountryDbModel
import com.mbobiosio.countries.model.domain.CountryDomainModel

fun List<Country>.convertToDbModel() : List<CountryDbModel> {
    return map {
        CountryDbModel(name = it.name!!, capital = it.capital, region = it.region, subregion = it.subregion,
            population = it.population, demonym = it.demonym, timezones = it.timezones, alpha2code = it.alpha2code,
            flag = it.flag, callingCodes = it.callingCodes, currencies = it.currencies)
    }
}

fun List<CountryDbModel>.convertToDomainModel() : List<CountryDomainModel> {
    return map {
        CountryDomainModel(name = it.name, capital = it.capital, region = it.region, subregion = it.subregion,
            population = it.population, demonym = it.demonym, timezones = it.timezones, alpha2code = it.alpha2code,
            flag = it.flag, callingCodes = it.callingCodes, currencies = it.currencies)
    }
}
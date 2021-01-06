package com.mbobiosio.countries.model

import java.io.Serializable

data class Country(
    val name: String?,
    val capital: String?,
    val region: String?,
    val subregion: String?,
    val population: Int?,
    val demonym: String?,
    val timezones: List<String>?,
    val alpha2code: String?,
    val flag: String?,
    val callingCodes: List<String>?,
    val currencies: List<Currencies>?
) : Serializable
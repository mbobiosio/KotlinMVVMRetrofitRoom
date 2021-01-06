package com.mbobiosio.countries.model

import java.io.Serializable

data class Country(
    val name: String?,
    val capital: String?,
    val region: String?,
    val subRegion: String?,
    val population: String?,
    val demonym: String?,
    val timeZones: List<String>?,
    val alpha2code: String?,
    val flag: String?,
    val callingCodes: List<String>?,
    val currencies: List<Currencies>?
) : Serializable
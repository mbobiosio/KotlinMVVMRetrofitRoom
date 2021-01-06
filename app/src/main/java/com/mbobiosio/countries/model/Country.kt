package com.mbobiosio.countries.model

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
)
data class Currencies(
    val code: String?,
    val name: String?,
    val symbol: String?
)
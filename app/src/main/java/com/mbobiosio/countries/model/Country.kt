package com.mbobiosio.countries.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Country : Serializable {

    @SerializedName("name")
    val name: String? = null

    @SerializedName("capital")
    val capital: String? = null

    @SerializedName("region")
    val region: String? = null

    @SerializedName("subregion")
    val subregion: String? = null

    @SerializedName("population")
    val population: String? = null

    @SerializedName("demonym")
    val demonym: String? = null

    @SerializedName("timezones")
    val timezones: List<String>? = null

    @SerializedName("alpha2Code")
    val alpha2Code: String? = null

    @SerializedName("flag")
    val flag: String? = null

    @SerializedName("callingCodes")
    val callingCodes: List<String>? = null

    @SerializedName("currencies")
    val currencies: List<Currencies> = ArrayList()

}

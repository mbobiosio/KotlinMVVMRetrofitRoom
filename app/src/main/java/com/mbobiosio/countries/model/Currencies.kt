package com.mbobiosio.countries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Currencies : Serializable {

    @SerializedName("code")
    @Expose
    val code: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("symbol")
    @Expose
    val symbol: String? = null

}
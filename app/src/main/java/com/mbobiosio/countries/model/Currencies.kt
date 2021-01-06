package com.mbobiosio.countries.model

import java.io.Serializable

data class Currencies(
    val code: String?,
    val name: String?,
    val symbol: String?
) : Serializable

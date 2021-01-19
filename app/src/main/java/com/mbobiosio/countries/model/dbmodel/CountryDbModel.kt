package com.mbobiosio.countries.model.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mbobiosio.countries.model.Currencies
import java.io.Serializable

@Entity(tableName = "country_table")
data class CountryDbModel(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "capital")
    val capital: String?,
    @ColumnInfo(name = "region")
    val region: String?,
    @ColumnInfo(name = "sub_region")
    val subregion: String?,
    @ColumnInfo(name = "population")
    val population: Int?,
    @ColumnInfo(name = "demonym")
    val demonym: String?,
    @ColumnInfo(name = "timezones")
    val timezones: List<String>?,
    @ColumnInfo(name = "alpha_2_code")
    val alpha2code: String?,
    @ColumnInfo(name = "flag")
    val flag: String?,
    @ColumnInfo(name = "calling_codes")
    val callingCodes: List<String>?,
    @ColumnInfo(name = "currencies")
    val currencies: List<Currencies>?
) : Serializable

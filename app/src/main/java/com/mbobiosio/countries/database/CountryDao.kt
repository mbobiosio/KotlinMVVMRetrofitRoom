package com.mbobiosio.countries.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mbobiosio.countries.model.dbmodel.CountryDbModel

@Dao
interface CountryDao {

    @Insert
    fun insertCountries(countries: List<CountryDbModel>)

    @Query("SELECT * FROM country_table ORDER BY countryId DESC")
    fun getCountries() : LiveData<List<CountryDbModel>>

    @Query("SELECT * FROM country_table WHERE countryId =:countryId LIMIT 1")
    fun getCountry(countryId: Long) : CountryDbModel
}
package com.mbobiosio.countries.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mbobiosio.countries.model.dbmodel.CountryDbModel

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountries(countries: List<CountryDbModel>)

    @Query("SELECT * FROM country_table ORDER BY name ASC")
    fun getCountries() : LiveData<List<CountryDbModel>>
}
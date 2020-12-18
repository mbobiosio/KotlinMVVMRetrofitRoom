package com.mbobiosio.countries.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mbobiosio.countries.R
import com.mbobiosio.countries.model.Country
import com.mbobiosio.countries.model.Currencies
import com.mbobiosio.countries.model.TimeZones
import com.mbobiosio.countries.ui.adapter.CountriesAdapter
import com.mbobiosio.countries.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var countryViewModel: CountryViewModel
    private lateinit var countriesAdapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        countriesAdapter = CountriesAdapter()
        countries.adapter = countriesAdapter

        observeDataList(countryViewModel)

    }

    private fun observeDataList(viewModel: CountryViewModel) {
        viewModel.fetchData(false).observe(this, {

            when {
                it.isNullOrEmpty() -> {
                    Timber.d("Empty")
                }
                else -> {
                    countriesAdapter.setData(it)

                }
            }


        })
    }
}
package com.mbobiosio.countries.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mbobiosio.countries.R
import com.mbobiosio.countries.model.Country
import com.mbobiosio.countries.ui.adapter.CountriesAdapter
import com.mbobiosio.countries.viewmodel.CountryViewModel
import com.mbobiosio.lifecycleconnectivity.LifecycleService
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), (Country) -> Unit {

    private lateinit var countryViewModel: CountryViewModel
    private lateinit var countriesAdapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        countriesAdapter = CountriesAdapter(this)
        countries.adapter = countriesAdapter

        val connectionLiveData = LifecycleService(this)
        connectionLiveData.observe(this, {

            observeDataList(countryViewModel)

            when (it) {
                true -> errorMessage.visibility = View.GONE
            }
        })

    }

    private fun observeDataList(viewModel: CountryViewModel) {
        viewModel.fetchData(false).observe(this, {
            countriesAdapter.setData(it)
        })
        viewModel.apiError.observe(this, {
            errorMessage.text = it
        })
        viewModel.networkError.observe(this, {
            errorMessage.text = getString(R.string.no_internet)
        })
        viewModel.progressBar.observe(this, {
            when {
                it -> {
                    isLoading(View.VISIBLE)
                }
                else -> {
                    isLoading(View.GONE)
                }
            }
        })

    }

    private fun isLoading(view: Int) {
        loadingIcon.visibility = view
    }

    override fun invoke(country: Country) {
        val intent = Intent(this, CountryDetailActivity::class.java)
        intent.putExtra("details", country)
        startActivity(intent)
    }

}
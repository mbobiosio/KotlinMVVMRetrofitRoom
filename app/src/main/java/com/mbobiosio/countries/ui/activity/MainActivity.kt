package com.mbobiosio.countries.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mbobiosio.countries.R
import com.mbobiosio.countries.databinding.ActivityMainBinding
import com.mbobiosio.countries.model.Country
import com.mbobiosio.countries.ui.adapter.CountriesAdapter
import com.mbobiosio.countries.viewmodel.CountryViewModel
import com.mbobiosio.lifecycleconnectivity.LifecycleService
import timber.log.Timber

class MainActivity : AppCompatActivity(), (Country) -> Unit {
    private val countryViewModel by viewModels<CountryViewModel>()
    private lateinit var countriesAdapter: CountriesAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        countriesAdapter = CountriesAdapter(this)
        binding.countries.adapter = countriesAdapter

        val connectionLiveData = LifecycleService(this)
        connectionLiveData.observe(this, {

            observeDataList(countryViewModel)

            when (it) {
                true -> {
                    binding.errorMessage.visibility = View.GONE
                }
            }
        })
    }

    private fun observeDataList(viewModel: CountryViewModel) {
        viewModel.fetchData(false).observe(this, {
            countriesAdapter.setData(it)
        })
        viewModel.apiError.observe(this, {
            binding.errorMessage.text = it
        })
        viewModel.networkError.observe(this, {
            binding.errorMessage.text = getString(R.string.no_internet)
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
        binding.loadingIcon.visibility = view
    }

    override fun invoke(country: Country) {
        val intent = Intent(this, CountryDetailActivity::class.java)
        //intent.putExtra("details", country)
        startActivity(intent)
    }
}
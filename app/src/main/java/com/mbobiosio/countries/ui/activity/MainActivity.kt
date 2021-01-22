package com.mbobiosio.countries.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.mbobiosio.countries.R
import com.mbobiosio.countries.databinding.ActivityMainBinding
import com.mbobiosio.countries.model.domain.CountryDomainModel
import com.mbobiosio.countries.ui.adapter.CountriesAdapter
import com.mbobiosio.countries.viewmodel.CountryViewModel
import com.mbobiosio.lifecycleconnectivity.LifecycleService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), (CountryDomainModel) -> Unit {
    private val countryViewModel by viewModels<CountryViewModel>()
    private lateinit var countriesAdapter: CountriesAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        countriesAdapter = CountriesAdapter(this)
        binding.countries.adapter = countriesAdapter
        observeDataList(countryViewModel)

        val connectionLiveData = LifecycleService(this)
        connectionLiveData.observe(this, {

            when (it) {
                true -> {
                    binding.errorMessage.visibility = View.GONE
                }
            }
        })
    }

    private fun observeDataList(viewModel: CountryViewModel) {
        lifecycleScope.launch {
            viewModel.fetchData(false).observe(this@MainActivity, {
                if (!it.isNullOrEmpty()) {
                    countriesAdapter.setData(it)
                    binding.errorMessage.visibility = View.GONE
                    isLoading(View.GONE)
                }
            })
        }
        viewModel.apiError.observe(this, {
            binding.errorMessage.text = it
        })
        viewModel.networkError.observe(this, {
            if (it) binding.errorMessage.text = getString(R.string.no_internet)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val menuItem = menu?.findItem(R.id.search)
        val searchView: SearchView = menuItem?.actionView as SearchView

        performSearch(searchView)

        return super.onCreateOptionsMenu(menu)
    }

    private fun performSearch(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                countriesAdapter.filter.filter(newText)
                return true
            }
        })
    }


    private fun isLoading(view: Int) {
        binding.loadingIcon.visibility = view
    }

    override fun invoke(country: CountryDomainModel) {
        val intent = Intent(this, CountryDetailActivity::class.java)
        intent.putExtra("details", country)
        startActivity(intent)
    }
}
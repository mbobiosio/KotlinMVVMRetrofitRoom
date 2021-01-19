package com.mbobiosio.countries.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.mbobiosio.countries.R
import com.mbobiosio.countries.databinding.ActivityCountryDetailBinding
import com.mbobiosio.countries.model.Country
import timber.log.Timber

class CountryDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountryDetailBinding

    private lateinit var country: Country

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_country_detail)
        binding.lifecycleOwner = this

        supportActionBar?.hide()

        country = (intent.getSerializableExtra("details") as? Country)!!
        binding.country = country

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}
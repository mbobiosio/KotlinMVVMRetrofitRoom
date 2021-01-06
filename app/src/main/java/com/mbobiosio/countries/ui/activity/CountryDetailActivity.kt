package com.mbobiosio.countries.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mbobiosio.countries.R
import com.mbobiosio.countries.model.Country
import kotlinx.android.synthetic.main.activity_country_detail.*

class CountryDetailActivity : AppCompatActivity() {

    private lateinit var country: Country

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        supportActionBar?.hide()

        country = (intent.getSerializableExtra("details") as? Country)!!

        countryName.text = country.name
        countryRegion.text = country.region
        countryCapital.text = country.capital
        countrySubRegion.text = country.subregion

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }
}
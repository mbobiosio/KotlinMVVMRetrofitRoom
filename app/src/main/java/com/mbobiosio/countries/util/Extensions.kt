package com.mbobiosio.countries.util

import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView
import java.text.DecimalFormat

/*
* Created by Mbuodile Obiosio on Jan 06, 2021.
* Twitter: @cazewonder
* Nigeria
*/
@BindingAdapter("population")
fun MaterialTextView.population(value: Int?) {
    if (value == null || value <= 0) {
        this.text = "-"
    } else {
        val popFormat = DecimalFormat("#,###,###")
        this.text = popFormat.format(value)
    }
}
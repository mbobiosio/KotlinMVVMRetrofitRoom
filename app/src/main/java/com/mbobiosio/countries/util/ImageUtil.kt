package com.mbobiosio.countries.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

/*
* Created by Mbuodile Obiosio on Jan 06, 2021.
* Twitter: @cazewonder
* Nigeria
*/
@BindingAdapter("setImage")
fun AppCompatImageView.setImage(url: String?) {
    url?.let {
        GlideApp.with(context).load(it).into(this)
    }
}

@BindingAdapter("flag")
fun AppCompatImageView.flag(url: String?) {
    url?.let {
        setImage(url)
    }
}
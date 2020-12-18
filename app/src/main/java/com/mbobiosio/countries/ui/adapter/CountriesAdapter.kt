package com.mbobiosio.countries.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mbobiosio.countries.R
import com.mbobiosio.countries.model.Country
import kotlinx.android.synthetic.main.item_country.view.*
import timber.log.Timber

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.CountriesVH>() {

    private var dataList: List<Country> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesVH {
        return CountriesVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_country, parent, false)
        )
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: CountriesVH, position: Int) =
        holder.bind(dataList[position])

    fun setData(data: List<Country>) {
        this.dataList = data
        notifyDataSetChanged()
    }

    class CountriesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Country) = with(itemView) {

            countryName.text = item.name
            capital.text = item.capital
            currency.text = item.currencies[0].code

            setOnClickListener {
                Timber.d("${item.name}")
            }
        }
    }
}
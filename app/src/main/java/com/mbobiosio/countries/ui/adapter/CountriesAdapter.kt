package com.mbobiosio.countries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbobiosio.countries.R
import com.mbobiosio.countries.model.Country
import com.mbobiosio.countries.util.GlideApp
import kotlinx.android.synthetic.main.item_country.view.*

class CountriesAdapter(
    var listener: (Country) -> Unit
) : RecyclerView.Adapter<CountriesAdapter.CountriesVH>() {

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

    inner class CountriesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Country) = with(itemView) {

            countryName.text = item.name
            capital.text = item.capital
            currency.text = item.currencies[0].code
            GlideApp.with(itemView.context).load(item.flag).into(flag)

            setOnClickListener {
                listener.invoke(item)
            }
        }
    }
}
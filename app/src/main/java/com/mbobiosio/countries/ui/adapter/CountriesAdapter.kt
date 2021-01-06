package com.mbobiosio.countries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mbobiosio.countries.R
import com.mbobiosio.countries.databinding.ItemCountryBinding
import com.mbobiosio.countries.model.Country
import com.mbobiosio.countries.util.GlideApp

class CountriesAdapter(
    var listener: (Country) -> Unit
) : RecyclerView.Adapter<CountriesAdapter.CountriesVH>(), Filterable {

    private var dataList: List<Country> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesVH {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountriesVH(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: CountriesVH, position: Int) =
        holder.bind(dataList[position])

    fun setData(data: List<Country>) {
        this.dataList = data
        notifyDataSetChanged()
    }

    inner class CountriesVH(private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Country) = with(itemView) {
            binding.country = item

            setOnClickListener {
                listener.invoke(item)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString()

                dataList = when {
                    query.isEmpty() -> dataList
                    else -> {
                        val mutableList: MutableList<Country> = mutableListOf()
                        for (data in dataList) {
                            if (data.name!!.contains(query, ignoreCase = true)
                                || data.capital!!.contains(query, ignoreCase = true)) {
                                mutableList.add(data)
                            }
                        }
                        mutableList
                    }
                }
                val results = FilterResults()
                results.values = dataList
                return results
            }
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataList = results?.values as List<Country>
                notifyDataSetChanged()
            }
        }
    }
}
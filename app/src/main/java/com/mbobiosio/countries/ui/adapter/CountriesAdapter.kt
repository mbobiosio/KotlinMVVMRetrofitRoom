package com.mbobiosio.countries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mbobiosio.countries.databinding.ItemCountryBinding
import com.mbobiosio.countries.model.domain.CountryDomainModel

class CountriesAdapter(
    var listener: (CountryDomainModel) -> Unit
) : RecyclerView.Adapter<CountriesAdapter.CountriesVH>(), Filterable {

    private var dataList: List<CountryDomainModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesVH {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountriesVH(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: CountriesVH, position: Int) =
        holder.bind(dataList[position])

    fun setData(data: List<CountryDomainModel>) {
        this.dataList = data
        notifyDataSetChanged()
    }

    inner class CountriesVH(private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CountryDomainModel) = with(itemView) {
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
                        val mutableList: MutableList<CountryDomainModel> = mutableListOf()
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
                dataList = results?.values as List<CountryDomainModel>
                notifyDataSetChanged()
            }
        }
    }
}
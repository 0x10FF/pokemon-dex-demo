package com.example.kotlin.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R
import com.example.kotlin.data.db.PokemonCatalogItem

class PokemonPagingListAdapter(
    private val onItemClicked: (PokemonCatalogItem) -> Unit = {}
) :
    PagingDataAdapter<PokemonCatalogItem, PokemonPagingListAdapter.PokemonListEntryViewHolder>(
        diffCallback = PokemonDiffer
    ) {

    override fun onBindViewHolder(holder: PokemonListEntryViewHolder, position: Int) {
        getItem(position)?.let { holder.bindView(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListEntryViewHolder {
        val containerView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_pokemon_list_entry, parent, false)
        return PokemonListEntryViewHolder(
            view = containerView,
            onItemClickedCallback = onItemClicked
        )
    }

    class PokemonListEntryViewHolder(
        private val view: View,
        private val onItemClickedCallback: (PokemonCatalogItem) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        fun bindView(item: PokemonCatalogItem) {
            val v = view.findViewById<TextView>(R.id.pokemon_name)
            v.text = item.name
            view.setOnClickListener {
                onItemClickedCallback(item)
            }
        }
    }

    object PokemonDiffer : DiffUtil.ItemCallback<PokemonCatalogItem>() {
        override fun areItemsTheSame(
            oldItem: PokemonCatalogItem,
            newItem: PokemonCatalogItem
        ): Boolean {
            return oldItem.name == newItem.name && oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: PokemonCatalogItem,
            newItem: PokemonCatalogItem
        ): Boolean {
            return oldItem.name == newItem.name && oldItem.url == newItem.url
        }
    }
}
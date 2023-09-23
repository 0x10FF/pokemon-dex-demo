package com.example.kotlin.view.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlin.R
import com.example.kotlin.data.db.PokemonCatalogItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private val adapter: PokemonPagingListAdapter?
        get() = view?.findViewById<RecyclerView>(R.id.pokemon_list)?.adapter as? PokemonPagingListAdapter

    private val viewModel: PokemonListViewModel by viewModels()

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        navController = this.findNavController()

        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        view.findViewById<RecyclerView>(R.id.pokemon_list).apply {
            adapter = PokemonPagingListAdapter(
                onItemClicked = this@PokemonListFragment::onItemClicked
            )
        }
        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            adapter?.refresh()
        }
        return view
    }

    private fun onItemClicked(item: PokemonCatalogItem) {
        Log.d(PokemonListFragment.TAG, "Pokemon id ${item.id}, clicked, talking to nav graph")
        val bundle = Bundle()
        item.id?.let { bundle.putInt(DetailsFragment.POKEMON_ENTITY_ID, it) }
        item.name?.let { bundle.putString(DetailsFragment.POKEMON_ENTITY_NAME, it) }
        navController.navigate(R.id.detailsFragment, bundle)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch { viewModel.pokemonCatalogFlow.collectLatest { populateList(it) } }
    }

    private suspend fun populateList(results: PagingData<PokemonCatalogItem>) {
        adapter?.submitData(results)
        swipeRefreshLayout.isRefreshing = false
    }

    companion object {
        val TAG = PokemonListFragment::class.simpleName
//        fun newInstance(): Fragment {
//            return PokemonListFragment()
//        }
    }
}

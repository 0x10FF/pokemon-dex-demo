package com.example.kotlin.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kotlin.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val detailsScreenViewModel = viewModels<DetailsScreenViewModel>()

    private var idTextView: TextView? = null
    private var heightTextView: TextView? = null
    private var weightTextView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
        idTextView = view.findViewById(R.id.pokemon_id_text_view)
        heightTextView = view.findViewById(R.id.pokemon_height_text_view)
        weightTextView = view.findViewById(R.id.pokemon_weight_text_view)
        return view
    }

    override fun onStart() {
        val pokemonIdToLoadFromApi = arguments?.getInt(POKEMON_ENTITY_ID)
        idTextView?.text = getString(R.string.pokemon_details_id_template, pokemonIdToLoadFromApi.toString())

        arguments?.getString(POKEMON_ENTITY_NAME)?.let { pokemonName ->
            lifecycleScope.launch {
                //TODO handling response in UI layer
                //move response handling into repo implementation
                val response = detailsScreenViewModel.value.getPokemonDetails(pokemonName)
                heightTextView?.text = getString(R.string.pokemon_details_height_template, response.body()?.height)
                weightTextView?.text = getString(R.string.pokemon_details_weight_template, response.body()?.weight)
            }
        }
        super.onStart()
    }

    companion object {
        const val POKEMON_ENTITY_ID = "pokemonIdToShow"
        const val POKEMON_ENTITY_NAME = "pokemonName"
    }
}
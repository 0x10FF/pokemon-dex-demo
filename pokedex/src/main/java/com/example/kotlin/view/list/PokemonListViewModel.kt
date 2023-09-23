package com.example.kotlin.view.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.example.kotlin.data.db.PokemonCatalogItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val pager: Pager<Int, PokemonCatalogItem>,

    ) : ViewModel() {

    val pokemonCatalogFlow = pager
        .flow
        //TODO any transformations

        //TODO end transformations
        .cachedIn(viewModelScope)
}
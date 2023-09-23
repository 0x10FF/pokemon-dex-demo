package com.example.kotlin.view.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.kotlin.data.PokemonListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val repository: PokemonListRepository,

    ) : ViewModel() {

    suspend fun getSpecificEntryFromCatalogOpen(id: Int) = withContext(Dispatchers.IO) {
        repository.getPokemon(id)
    }

    suspend fun getPokemonDetails(name: String) = withContext(Dispatchers.IO) {
        repository.getPokemonDetails(name)
    }
}
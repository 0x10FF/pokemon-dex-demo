package com.example.kotlin.data.model

import com.example.kotlin.data.model.PokemonListEntryResult

data class PokemonListResult(
    val nextPage: Int?,
    val prevPage: Int?,
    val results: List<PokemonListEntryResult>
)
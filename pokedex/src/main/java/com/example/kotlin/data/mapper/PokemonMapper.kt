package com.example.kotlin.data.mapper

import com.example.kotlin.data.db.PokemonCatalogItem
import com.example.kotlin.data.model.PokemonListEntryResult

fun PokemonListEntryResult.toPokemonEntity(): PokemonCatalogItem {
    return PokemonCatalogItem(
        name = name,
        url = url
    )
}

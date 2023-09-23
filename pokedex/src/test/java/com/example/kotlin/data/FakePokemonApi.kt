package com.example.kotlin.data

import retrofit2.Response

class FakePokemonApi : PokemonApi {
    override suspend fun pokemonList(
        offset: Int,
        limit: Int
    ): Response<PokemonApi.PokemonListResponse> {
        TODO("Not yet implemented")
    }
}
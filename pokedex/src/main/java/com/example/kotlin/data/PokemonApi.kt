package com.example.kotlin.data

import com.example.kotlin.data.model.PokemonListEntryResult
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun pokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20
    ): Response<PokemonListResponse>


    data class PokemonListResponse(
        @SerializedName("count") val count: String,
        @SerializedName("next") val next: String?,
        @SerializedName("previous") val previous: String?,
        @SerializedName("results") val results: List<PokemonListEntryResult>
    )

    @GET("pokemon/{name}")
    suspend fun pokemon(
        @Path("name") name: String,
    ): Response<PokemonDetailResponse>
    data class PokemonDetailResponse(
        @SerializedName("weight") val weight: String?,
        @SerializedName("height") val height: String?,
    )

}
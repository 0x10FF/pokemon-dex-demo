package com.example.kotlin.data.model

import com.google.gson.annotations.SerializedName

data class PokemonListEntryResult(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
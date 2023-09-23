package com.example.kotlin.data

import android.net.Uri
import com.example.kotlin.data.model.UriTypeAdapter
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Deprecated(message = "Moved to Dagger/Hilt")
object ApiClient {
//    private var client: PokemonApi? = null
//    fun api(): PokemonApi? {
//        return client ?: initializeClient()
//    }
//
//    private fun initializeClient(): PokemonApi? {
//        val gson = GsonBuilder()
//        gson.registerTypeAdapter(Uri::class.java, UriTypeAdapter())
//        val gsonConverterFactory = GsonConverterFactory.create(gson.create())
//        client = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
//            .client(HttpClient.client)
//            .addConverterFactory(gsonConverterFactory)
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .build()
//            .create(PokemonApi::class.java)
//        return client
//    }
}
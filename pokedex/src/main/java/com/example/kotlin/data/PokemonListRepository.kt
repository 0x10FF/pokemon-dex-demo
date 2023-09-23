package com.example.kotlin.data

import android.net.Uri
import com.example.kotlin.data.db.PokemonCatalogItem
import com.example.kotlin.data.db.PokemonDatabase
import com.example.kotlin.data.model.PokemonListResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonListRepository @Inject constructor(
    private val api: PokemonApi,
    val db: PokemonDatabase
) {
    suspend fun getPokemonListResults(page: Int): PokemonListResult =
        withContext(Dispatchers.IO) {
            val response = api.pokemonList(page * PER_PAGE)
            response.body()?.let {
                PokemonListResult(
                    nextPage = getPageFromUrl(it.next),
                    prevPage = getPageFromUrl(it.previous),
                    results = it.results
                )
            } ?: PokemonListResult(null, null, emptyList())
        }

    suspend fun getPokemon(id: Int): PokemonCatalogItem = withContext(Dispatchers.IO) {
        db.dao().loadAllByIds(intArrayOf(id)).first()
    }

    //TODO importing Dto vs Domain Object
    suspend fun getPokemonDetails(name: String): Response<PokemonApi.PokemonDetailResponse> = withContext(Dispatchers.IO) {
        api.pokemon(name)
    }

    private fun getPageFromUrl(urlString: String?): Int? {
        return urlString?.let {
            Uri.parse(it).getQueryParameter(PARAM_OFFSET)?.toInt()?.div(PER_PAGE)
        }
    }
    companion object {
        private const val PARAM_OFFSET = "offset"
        const val PER_PAGE = 20
    }
}
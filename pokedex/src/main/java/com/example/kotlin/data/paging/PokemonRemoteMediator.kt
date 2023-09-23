package com.example.kotlin.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.kotlin.data.PokemonApi
import com.example.kotlin.data.db.PokemonDatabase
import com.example.kotlin.data.db.PokemonCatalogItem
import com.example.kotlin.data.mapper.toPokemonEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class PokemonRemoteMediator @Inject constructor(
    private val pokemonDb: PokemonDatabase,
    private val pokemonApi: PokemonApi
) : RemoteMediator<Int, PokemonCatalogItem>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonCatalogItem>
    ): MediatorResult {

        return try {

            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1

                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id?.div(state.config.pageSize))?.plus(1)
                    }
                }
            }

            val offset = (loadKey?.times(state.config.pageSize)?.minus(state.config.pageSize)) ?: 0

            val pokemon = pokemonApi
                .pokemonList(offset)
                .body()
                ?.results

            pokemonDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pokemonDb.dao().deleteAll()
                }
                val pokemonEntities = pokemon?.map { it.toPokemonEntity()}
                pokemonDb.dao().insertAll(pokemonEntities)
            }

            return MediatorResult.Success(
                endOfPaginationReached = pokemon?.isEmpty() == true
            )
        } catch(io: IOException) {
            MediatorResult.Error(io)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
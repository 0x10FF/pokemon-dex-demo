package com.example.kotlin.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlin.data.db.PokemonCatalogItem

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonCatalogItem")
    suspend fun getAll(): List<PokemonCatalogItem>

    @Query("SELECT * FROM PokemonCatalogItem WHERE id IN (:ids)")
    suspend fun loadAllByIds(ids: IntArray): List<PokemonCatalogItem>

    @Insert
    suspend fun insertAll(pokemons: List<PokemonCatalogItem>?)

    @Delete
    suspend fun delete(pokemon: PokemonCatalogItem)

    @Query("delete from PokemonCatalogItem")
    suspend fun deleteAll()

    @Query("SELECT * FROM PokemonCatalogItem")
    fun pagingSource(): PagingSource<Int, PokemonCatalogItem>
}
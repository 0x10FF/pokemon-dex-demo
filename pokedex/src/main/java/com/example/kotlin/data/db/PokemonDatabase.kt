package com.example.kotlin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlin.data.dao.PokemonDao

@Database(
    entities = [PokemonCatalogItem::class],
    version = 1
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun dao(): PokemonDao

    companion object {
        const val dbFileName = "pokemon_catalog.db"
    }
}
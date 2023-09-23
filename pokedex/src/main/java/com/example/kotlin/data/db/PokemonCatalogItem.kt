package com.example.kotlin.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PokemonCatalogItem(
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "url") val url: String?,
    @PrimaryKey val id: Int? = null
)
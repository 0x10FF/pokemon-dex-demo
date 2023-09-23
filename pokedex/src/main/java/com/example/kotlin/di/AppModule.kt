package com.example.kotlin.di

import android.content.Context
import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.kotlin.data.PokemonApi
import com.example.kotlin.data.PokemonListRepository
import com.example.kotlin.data.db.PokemonDatabase
import com.example.kotlin.data.db.PokemonCatalogItem
import com.example.kotlin.data.model.UriTypeAdapter
import com.example.kotlin.data.paging.PokemonRemoteMediator
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonApi() : PokemonApi {
        return Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    })
                    .build()
            ).addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .registerTypeAdapter(Uri::class.java, UriTypeAdapter())
                        .create()
                )
            )
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun providesPokemonDatabase(@ApplicationContext context: Context) : PokemonDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = PokemonDatabase::class.java,
            name = PokemonDatabase.dbFileName
        ).build()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providesPager(api: PokemonApi, db: PokemonDatabase,) : Pager<Int, PokemonCatalogItem> {
        return Pager(
            config = PagingConfig(
                pageSize = PokemonListRepository.PER_PAGE
            ),
            remoteMediator = PokemonRemoteMediator(
                pokemonDb = db,
                pokemonApi = api
            ),
            pagingSourceFactory = {
                db.dao().pagingSource()
            }
        )
    }
}
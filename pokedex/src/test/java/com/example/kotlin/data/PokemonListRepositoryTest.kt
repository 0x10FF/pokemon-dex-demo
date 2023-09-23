package com.example.kotlin.data

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PokemonListRepositoryTest {

    private lateinit var repoUnderTest: PokemonListRepository

    @Before
    fun setUp() {
        val api = FakePokemonApi()
        //val db = FakePokemonDatabase()

        //TODO add repo interface
        //TODO insert some fake data

        //repoUnderTest = PokemonListRepository(api, db)
    }

    @Test
    fun `test that an item returns`() = runBlocking {
        //TODO introduce an interface for repo
        repoUnderTest.getPokemon(1)
    }

}
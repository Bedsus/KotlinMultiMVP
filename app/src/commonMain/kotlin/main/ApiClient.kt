package main

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import main.data.Pokedex
import main.data.PokemonEntry

class ApiClient {
    private val httpClient = HttpClient()

    fun getPokemonList(success: (List<PokemonEntry>) -> Unit, failure: (Throwable?) -> Unit) {

        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val url = "https://pokeapi.co/api/v2/pokedex/kanto/"
                val json = httpClient.get<String>(url)
                Json.nonstrict.parse(Pokedex.serializer(), json)
                    .pokemon_entries
                    .also(success)
            } catch (ex: Exception) {
                failure(ex)
            }
        }
    }

}

internal expect val ApplicationDispatcher: CoroutineDispatcher
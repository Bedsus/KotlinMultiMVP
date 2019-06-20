package main

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ApiClient {
    private val httpClient = HttpClient()
    private val url = "https://pokeapi.co/api/v2/pokedex/kanto/"

    fun getPokemonList(success: (List<PokemonEntry>) -> Unit, failure: (Throwable?) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                Json.nonstrict.parse(Pokedex.serializer(), httpClient.get(url))
                    .pokemon_entries
                    .also(success)
            } catch (ex: Exception) {
                failure(ex)
            }
        }
    }

}
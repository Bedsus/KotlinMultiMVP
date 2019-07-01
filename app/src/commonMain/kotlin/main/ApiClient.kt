package main

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json
import main.data.FlavorTextEntries
import main.data.Pokedex


class ApiClient {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json.nonstrict)
        }
    }

    private fun HttpRequestBuilder.json() {
        contentType(ContentType.Application.Json)
    }

    private fun HttpRequestBuilder.apiUrl(url: String, path: String) {
        header(HttpHeaders.CacheControl, "no-cache")
        url {
            takeFrom(url)
            encodedPath = path
        }
    }

    /**
     * Url нужно прописывать основной, иначе все падает
     */
    suspend fun getPokemonList(): Pokedex = client.get {
        val url = "https://pokeapi.co"
        apiUrl(url, "api/v2/pokedex/kanto/")
    }

    suspend fun getPokemonInfo(id: Int): FlavorTextEntries = client.get {
        val url = "https://pokeapi.co"
        apiUrl(url, "api/v2/pokemon-species/$id/")
    }
}
package main

class MainModelImpl : MainModel {

    private val apiClient = ApiClient()

    override suspend fun getPokemonList() = apiClient.getPokemonList()

    override suspend fun getPokemonInfo(id: Int) = apiClient.getPokemonInfo(id)
        .flavor_text_entries
        .asSequence()
        .filter { it.version.name == "red" || it.version.name == "blue" || it.version.name == "yellow" }
        .filter { it.language.name == "en" }
        .toList()
        .firstOrNull()
        ?.flavor_text ?: "Нет описания"
}
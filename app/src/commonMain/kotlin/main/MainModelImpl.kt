package main

class MainModelImpl : MainModel {

    private val apiClient = ApiClient()

    override suspend fun getPokemonList() = apiClient.getPokemonList()

}
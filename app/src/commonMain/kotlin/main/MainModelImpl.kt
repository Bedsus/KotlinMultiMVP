package main

class MainModelImpl : MainModel {

    private val apiClient = ApiClient()

    override fun getPokemonList(success: (List<PokemonEntry>) -> Unit, failure: (Throwable?) -> Unit) {
        apiClient.getPokemonList(success, failure)
    }





}
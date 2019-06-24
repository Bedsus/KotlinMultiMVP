package main

import main.data.PokemonEntry

interface MainView {
    fun showPlatformName(name: String)
    fun showPokemonList(pokemons: List<PokemonEntry>)
    fun handleError(ex: Throwable?)
}

interface MainPresenter

interface MainModel {
    fun getPokemonList(success: (List<PokemonEntry>) -> Unit, failure: (Throwable?) -> Unit)
}
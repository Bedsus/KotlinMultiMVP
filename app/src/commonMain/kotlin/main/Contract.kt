package main

import main.data.Pokedex
import main.data.PokemonEntry

interface MainView : BaseView {
    fun showPlatformName(name: String)
    fun showPokemonList(pokemons: List<PokemonEntry>)
}

interface MainPresenter: BasePresenter

interface MainModel {
    suspend fun getPokemonList(): Pokedex
}
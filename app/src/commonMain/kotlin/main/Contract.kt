package main

import main.data.Pokedex
import main.data.PokemonEntry

interface MainView : BaseView {
    fun showPlatformName(name: String)
    fun showPokemonList(pokemons: List<PokemonEntry>)
    /**
     * Переход к описанию опеределенного покемона
     * @param id номер покемона
     */
    fun goToPokemonInfo(id: Int)
}

interface MainPresenter: BasePresenter

interface MainModel {
    suspend fun getPokemonList(): Pokedex
    suspend fun getPokemonInfo(id: Int): String
}
package main.pokemon_info

import main.BasePresenter
import main.BaseView

interface PokemonInfoView : BaseView {
    fun showPokemonInfo(pokemonInfo: String)
}

interface PokemonInfoPresenter: BasePresenter
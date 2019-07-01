package main.pokemon_info

import kotlinx.coroutines.launch
import main.CoroutinePresenter
import main.MainModelImpl
import kotlin.coroutines.CoroutineContext

class PokemonInfoPresenterImpl(
    uiContext: CoroutineContext,
    private val id: Int,
    private val view: PokemonInfoView,
    private val model: MainModelImpl
) : PokemonInfoPresenter, CoroutinePresenter(uiContext, view) {

    init {
        showPokemonInfo()
    }

    private fun showPokemonInfo(){
        view.showLoading()
        launch {
            try {
                view.showPokemonInfo(
                    model.getPokemonInfo(id)
                )
            } catch (ex: Exception) {
                view.showError(ex.message ?: "Unknown error")
            }
            view.hideLoading()
        }
    }
}
package main

import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainPresenterImpl(
    uiContext: CoroutineContext,
    private val view: MainView,
    private val model: MainModelImpl
) : MainPresenter, CoroutinePresenter(uiContext, view) {

    init {
        showPokemonList()
        view.showPlatformName("${getPlatformName()} Pokemon List")
    }

    private fun showPokemonList(){
        launch {
            try {
                view.showPokemonList(
                     model.getPokemonList()
                         .pokemon_entries
                )
            } catch (ex: Exception) {
                view.showError(ex.message ?: "Unknown error")
            }
        }
    }
}

expect fun getPlatformName(): String
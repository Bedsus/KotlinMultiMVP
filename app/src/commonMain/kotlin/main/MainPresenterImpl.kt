package main

class MainPresenterImpl(view: MainView, model: MainModelImpl) : MainPresenter {
    init {
        model.getPokemonList(
            success = { view.showPokemonList(it) },
            failure = { view.showError() }
        )
        view.showPlatformName("${getPlatformName()} Pokemon List")
    }
}

expect fun getPlatformName(): String
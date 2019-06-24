package main

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainPresenterImpl(view: MainView, model: MainModelImpl) : MainPresenter, CoroutineScope {

    /** Время жизни актиквити (найти более красивое решение) **/
    private val job: Job = Job()

    /**
     * CoroutineScope требует указать контекст.
     * Указываем основоной поток вместе с активити
     **/
    override val coroutineContext: CoroutineContext
        get() = job + Main

    init {
        model.getPokemonList(
            success = { launch(Main) { view.showPokemonList(it)} },
            failure = { launch(Main) { view.handleError(it) } }
        )
        view.showPlatformName("${getPlatformName()} Pokemon List")
    }

    override fun detachView() {
        job.cancel()
    }
}

expect fun getPlatformName(): String
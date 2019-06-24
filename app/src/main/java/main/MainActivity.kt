package main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import main.data.PokemonEntry
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), MainView, CoroutineScope {

    /** Время жизни актиквити (найти более красивое решение) **/
    private lateinit var job: Job

    /**
     * CoroutineScope требует указать контекст.
     * Указываем основоной поток вместе с активити
    **/
    override val coroutineContext: CoroutineContext
        get() = job + Main

    private lateinit var presenter : MainPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()
        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view_list).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }
        presenter = MainPresenterImpl(this, MainModelImpl())
    }

    override fun showPlatformName(name: String) {
        title = name
    }

    override fun handleError(ex: Throwable?) {
        ex?.printStackTrace()
        launch(Main) {
            val msg : String = ex?.message ?: "Unknown error"
            Toast.makeText(baseContext, msg, Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun showPokemonList(pokemons: List<PokemonEntry>) {
        recyclerView.adapter = PokemonAdapter(pokemons)
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}
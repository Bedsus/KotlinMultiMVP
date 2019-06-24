package main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import main.data.PokemonEntry

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter : MainPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        val msg : String = ex?.message ?: "Unknown error"
        Toast.makeText(this, msg, Toast.LENGTH_LONG)
            .show()
    }

    override fun showPokemonList(pokemons: List<PokemonEntry>) {
        recyclerView.adapter = PokemonAdapter(pokemons)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
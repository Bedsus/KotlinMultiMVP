package main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import main.data.PokemonEntry
import main.pokemon_info.PokemonInfoActivity

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter : MainPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view_list).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }
        progressBar = findViewById(R.id.pBar)
        presenter = MainPresenterImpl(Dispatchers.Main, this, MainModelImpl())
    }

    override fun showPlatformName(name: String) {
        title = name
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG)
            .show()
    }

    override fun showPokemonList(pokemons: List<PokemonEntry>) {
        recyclerView.adapter = PokemonAdapter(pokemons, View.OnClickListener {
            val id = recyclerView.getChildLayoutPosition(it) + 1
            goToPokemonInfo(id)
        })
    }

    override fun goToPokemonInfo(id: Int) = startActivity(
        Intent(this, PokemonInfoActivity::class.java).apply {
            putExtra("POKEMON_ID_TAG", id)
        })

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
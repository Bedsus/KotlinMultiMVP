package main.pokemon_info

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import main.MainModelImpl
import main.R
import main.data.getUrlPokemonImage

class PokemonInfoActivity : AppCompatActivity(), PokemonInfoView {
    private lateinit var presenter : PokemonInfoPresenter
    private lateinit var vImage: ImageView
    private lateinit var vInfoText: TextView
    private var pokemonId: Int = 0
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_info)
        vImage = findViewById(R.id.pokemonImage)
        vInfoText = findViewById(R.id.pokemonInfoText)
        pokemonId = intent.extras?.getInt("POKEMON_ID_TAG") ?: 0
        progressBar = findViewById(R.id.pBar)
        presenter = PokemonInfoPresenterImpl(Dispatchers.Main, pokemonId, this, MainModelImpl())
    }

    override fun showPokemonInfo(pokemonInfo: String) {
        vInfoText.text = pokemonInfo
        Picasso.get()
            .load(getUrlPokemonImage(pokemonId))
            .into(vImage)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG)
            .show()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}
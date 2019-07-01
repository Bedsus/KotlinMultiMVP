package main.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Pokedex(val pokemon_entries: List<PokemonEntry>)

@Serializable
data class PokemonEntry(private val entry_number: Int, private val pokemon_species: Pokemon) {
    @Transient
    val label
        get() = pokemon_species.name
            .toUpperCase()

    @Transient
    val number
        get() = entry_number.toString()
            .padStart(3, '0')

    @Transient
    val urlImage
        get() = getUrlPokemonImage(entry_number)
}


@Serializable
data class Pokemon(val name: String, val url: String)

fun getUrlPokemonImage(id: Int) = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
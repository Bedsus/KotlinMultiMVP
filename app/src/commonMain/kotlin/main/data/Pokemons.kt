package main

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Pokedex(val pokemon_entries: List<PokemonEntry>)

@Serializable
data class PokemonEntry(val entry_number: Int, val pokemon_species: Pokemon) {
    @Transient
    val label: String
        get() {
            val name = pokemon_species.name.toUpperCase()
            return "N°$entry_number\t\t$name"
        }

}


@Serializable
data class Pokemon(val name: String, val url: String)
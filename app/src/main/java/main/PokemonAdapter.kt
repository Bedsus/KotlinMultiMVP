package main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import main.PokemonAdapter.MyViewHolder
import main.data.PokemonEntry


class PokemonAdapter(
    private val myDataSet: List<PokemonEntry>,
    private val mOnClickListener: View.OnClickListener
) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =  LayoutInflater.from(parent.context)
            .inflate(R.layout.my_text_view, parent, false)
        view.setOnClickListener(mOnClickListener)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pokemon = myDataSet[position]
        holder.textView.text = pokemon.label
        holder.number.text = pokemon.number
        Picasso.get()
            .load(pokemon.urlImage)
            .centerCrop()
            .resize(300, 300)
            .placeholder(R.drawable.progress_animation)
            .into(holder.imageView)
    }

    override fun getItemCount() = myDataSet.size

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val number: TextView = view.findViewById(R.id.number)
        val imageView: ImageView = view.findViewById(R.id.pokemonImage)
    }
}

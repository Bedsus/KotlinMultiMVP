package main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MyAdapter(private val myDataSet: List<PokemonEntry>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
         MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.my_text_view, parent, false))


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = myDataSet[position].label
        Picasso.get()
            .load("http://pngimg.com/uploads/pokemon/pokemon_PNG156.png")
            .into(holder.imageView)
    }

    override fun getItemCount() = myDataSet.size

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }
}

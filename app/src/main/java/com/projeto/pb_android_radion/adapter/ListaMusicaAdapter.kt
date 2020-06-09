package com.projeto.pb_android_radion.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projeto.pb_android_radion.R
import com.projeto.pb_android_radion.model.Musica
import kotlinx.android.synthetic.main.lista_musica.view.*

class ListaMusicaAdapter(val musica:List<Musica>, val callback:(Musica, Context) -> Unit

)
:RecyclerView.Adapter<ListaMusicaAdapter.MusicaViewHolder>() {
    class MusicaViewHolder (view: View)
        : RecyclerView.ViewHolder(view){
        val txtViewNomeMusica = view.txtNomeMusica
        val textViewNomeAutor = view.txtNomeAutor

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MusicaViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.lista_musica,
                parent,
                false
            )
        val musicaViewHolder =
            MusicaViewHolder(
                view
            )
        musicaViewHolder.itemView.setOnClickListener {
            val musica = musica[musicaViewHolder.adapterPosition]
            callback(musica, parent.context)
        }
       /* musicaViewHolder.itemView.setOnClickListener{
            val musica = musica[musicaViewHolder.adapterPosition]

        }
*/
        return musicaViewHolder
    }

    override fun getItemCount(): Int = musica.size


    override fun onBindViewHolder(holder: MusicaViewHolder, position: Int) {
        val musica = musica[position]
        holder.txtViewNomeMusica.text = musica.nomeMusica
        holder.textViewNomeAutor.text = musica.artista
    }
}
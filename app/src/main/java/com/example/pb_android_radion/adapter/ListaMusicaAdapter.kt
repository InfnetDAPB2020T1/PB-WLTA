package com.example.pb_android_radion.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pb_android_radion.R
import com.example.pb_android_radion.model.Musica
import com.example.pb_android_radion.model.Search
import com.example.pb_android_radion.viewModel.MusicaViewModel
import kotlinx.android.synthetic.main.lista_musica.view.*

class ListaMusicaAdapter(val musica:List<Musica>

)
:RecyclerView.Adapter<ListaMusicaAdapter.musicaViewHolder>() {
    class musicaViewHolder (view: View)
        : RecyclerView.ViewHolder(view){
        val txtViewNomeMusica = view.txtNomeMusica
        val textViewNomeAutor = view.txtNomeAutor

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : musicaViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.lista_musica,
                parent,
                false
            )
        val musicaViewHolder =
            musicaViewHolder(
                view
            )
       /* musicaViewHolder.itemView.setOnClickListener{
            val musica = musica[musicaViewHolder.adapterPosition]

        }
*/
        return musicaViewHolder
    }

    override fun getItemCount(): Int = musica.size


    override fun onBindViewHolder(holder: musicaViewHolder, position: Int) {
        val musica = musica[position]
        holder.txtViewNomeMusica.text = musica.nomeMusica
        holder.textViewNomeAutor.text = musica.nomeCantor
    }
}
package com.projeto.pb_android_radion.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projeto.pb_android_radion.R
import kotlinx.android.synthetic.main.layout_search.view.*

class SearchAdapter(
    val musicaSearch: ArrayList<String>
)
    :RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var mExpandedPosition = -1
    private var previousExpandedPosition = -1
    private val recyclerView: RecyclerView? = null

    class SearchViewHolder(
        view: View
    )
        : RecyclerView.ViewHolder(view){

        var textViewNomeMusica = view.textViewNomeMusica
        var textViewNomeCantor = view.textViewNomeCantorBanda
      //  var textViewNomeAlbum = view.textViewNomeAlbum
       // var imagem: ImageManager? = null
       var expandableLayout = view.expandableLayout
/*

        textViewNomeMusica.setOnClickListener {
                val movie = SearchEvent.get(getAdapterPosition())
                movie.setExpanded(!movie.isExpanded());
                notifyItemChanged(getAdapterPosition());

            }
*/


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : SearchViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.layout_search,
                parent,
                false
            )
        val searchViewHolder =
            SearchViewHolder(
                view
            )
        return searchViewHolder
    }

    override fun getItemCount(): Int = musicaSearch.size


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        val musica = musicaSearch[position]
        holder.textViewNomeMusica.text = "musica.nomeMusica"
        holder.textViewNomeCantor.text = "musica.nomeCantor"
       // holder.textViewNomeAlbum.text = "musica.nomeAlbum"
     //   holder.expandableLayout = musicaSearch.get(position).isExpanded()

        val isExpanded = position === mExpandedPosition

        //ESSE
        holder.expandableLayout.setVisibility(if (isExpanded) View.VISIBLE else View.GONE)
        holder.itemView.isActivated = isExpanded

        if (isExpanded) previousExpandedPosition = position

        holder.itemView.setOnClickListener {
            mExpandedPosition = if (isExpanded) -1 else position
            notifyItemChanged(previousExpandedPosition)
            notifyItemChanged(position)
        }

       /* //OU ESSE
        holder.expandableLayout.setVisibility(if (isExpanded) View.VISIBLE else View.GONE)
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener {
            mExpandedPosition = if (isExpanded) -1 else position
            TransitionManager.beginDelayedTransition(recyclerView)
          //  arcoExpansao.setBackgroundResource(R.drawable.ic_expand_less_black_24dp)
            notifyDataSetChanged()
        }*/
       /* val  isExpanded : Boolean = musicaSearch[position].isExpanded()
            holder.expandableLayout[position].visibility*/
    }

    //(if (isExpanded) View.VISIBLE else View.GONE)
}



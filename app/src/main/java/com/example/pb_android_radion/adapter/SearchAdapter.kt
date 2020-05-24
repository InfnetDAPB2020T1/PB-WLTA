package com.example.pb_android_radion.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pb_android_radion.R
import com.example.pb_android_radion.SearchFragment
import com.example.pb_android_radion.model.Musica
import com.example.pb_android_radion.model.SearchModel
import kotlinx.android.synthetic.main.country_child.view.*
import kotlinx.android.synthetic.main.lista_musica.view.*

/*class SearchAdapter( val musica: List<SearchModel>

)
    : RecyclerView.Adapter<ListaMusicaAdapter.musicaViewHolder>() {
    class searchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.country_name

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : searchViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.country_child,
                parent,
                false
            )
        val searchViewHolder =
            searchViewHolder(
                view
            )
        return searchViewHolder
    }

    override fun getItemCount(): Int = musica.size

    override fun onBindViewHolder(holder: searchViewHolder, position: Int) {
        val musica = musica[position]
        holder.name.text = musica.nomeCantor
    }

    override fun onBindViewHolder(holder: ListaMusicaAdapter.musicaViewHolder, position: Int) {
        TODO("Not yet implemented")
    }*/

class SearchAdapter(items : List<String>,ctx: Context) : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){

    private var list = items
    private var context = ctx

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val name = v.country_name!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.name?.text = list[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.country_child,parent,false))
    }
}
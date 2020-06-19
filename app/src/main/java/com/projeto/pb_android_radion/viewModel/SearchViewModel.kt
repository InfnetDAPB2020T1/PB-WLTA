package com.projeto.pb_android_radion.viewModel

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.projeto.pb_android_radion.R
import com.projeto.pb_android_radion.adapter.ListaMusicaAdapter
import com.projeto.pb_android_radion.apiService.ApiClient
import com.projeto.pb_android_radion.model.MusicList
import com.projeto.pb_android_radion.model.Musica
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_musica_api.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchViewModel : ViewModel(){

    private lateinit var firebaseFirestore: FirebaseFirestore

    fun searchMusica(recycleView: RecyclerView, context: Context, pesquisa: String){
        if(pesquisa.isBlank()){
            Toast.makeText(context, "Preencha o campo de pesquisa", Toast.LENGTH_LONG).show()
            return
        }
        firebaseFirestore = FirebaseFirestore.getInstance()
        val collection = firebaseFirestore.collection("music")

        val task = collection.get()

        task.addOnSuccessListener {

            if (it != null){
                //Pega resultado da consulta
                val musicas = it.toObjects(Musica::class.java)
                val searchMusica = mutableListOf<Musica>()

                musicas.forEach {
                    if(it.nomeMusica?.toUpperCase()!!.contains(pesquisa.toUpperCase())
                        ||
                        it.artista?.toUpperCase()!!.contains(pesquisa.toUpperCase())){

                        var musica = Musica(it.nomeMusica, it.artista)
                        searchMusica.add(musica)
                    }
                }

                //Alimentando a recycle
                if(searchMusica.size == 0){
                    Toast.makeText(context, "Nenhum resultado foi encontrado!", Toast.LENGTH_LONG).show()
                }
                recycleView.adapter = ListaMusicaAdapter(searchMusica, this::callbackSearchMusicas)
                recycleView.layoutManager = LinearLayoutManager(context)

            }else{
                Toast.makeText(context, "Listagem vazia.", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener{
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
    }

    fun callbackSearchMusicas(musica: Musica, context: Context){
        val myDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_musica_api, null)
        val myBuilder = AlertDialog.Builder(context)
            .setView(myDialogView)
            .setTitle("Informações Detalhadas")

        val myAlertDialog = myBuilder.show()
        val artista_musica = "${musica.artista.toString()} ${musica.nomeMusica.toString()}"

        ApiClient.getMusicasService()
            .show(artista_musica)
            .enqueue(object : Callback<MusicList> {
                override fun onFailure(call: Call<MusicList>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    Log.e("ERROAPI", t.message)
                }

                override fun onResponse(
                    call: Call<MusicList>,
                    response: Response<MusicList>
                ) {
                    val lista = response.body()
                    var achou = false

                    lista!!.data.forEach {
                        if(it.artist?.name?.toUpperCase().toString() == musica.artista?.toUpperCase().toString()
                            && it.title?.toUpperCase().toString() == musica.nomeMusica?.toUpperCase().toString()){

                            val imageView: ImageView = myDialogView.findViewById(R.id.imgViewFotoAlbum)
                            val imageUrl = it.album?.cover_medium

                            Picasso.get().load(imageUrl).into(imageView)
                            myDialogView.textViewArtista.setText(it.artist?.name)
                            myDialogView.textViewMusica.setText(it.title)
                            myDialogView.textViewTempoTotal.setText(" ${it.duration} segundos")
                            myDialogView.textViewAlbum.setText(it.album?.title)

                            achou = true

                            return
                        }
                    }
                    if(!achou){
                        val imageView: ImageView = myDialogView.findViewById(R.id.imgViewFotoAlbum)
                        val imageUrl = lista!!.data[0].album?.cover_medium

                        Picasso.get().load(imageUrl).into(imageView)
                        myDialogView.textViewArtista.setText(lista!!.data[0].artist?.name)
                        myDialogView.textViewMusica.setText(lista!!.data[0].title)
                        myDialogView.textViewTempoTotal.setText("${lista!!.data[0].duration} segundos")
                        myDialogView.textViewAlbum.setText(lista!!.data[0].album?.title)
                    }
                }
            })

        myDialogView.btnVoltar.setOnClickListener {
            //Botão para fechar o dialog, pode ser um daqueles floatbutton com um x em lá em cima
            myAlertDialog.dismiss()
        }
    }
}
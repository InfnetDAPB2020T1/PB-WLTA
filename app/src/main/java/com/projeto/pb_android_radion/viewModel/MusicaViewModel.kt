package com.projeto.pb_android_radion.viewModel

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projeto.pb_android_radion.adapter.ListaMusicaAdapter
import com.projeto.pb_android_radion.model.Musica
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.projeto.pb_android_radion.R
import com.projeto.pb_android_radion.service.MusicaService
import kotlinx.android.synthetic.main.dialog_musica_api.view.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response

class MusicaViewModel :  ViewModel() {

    var musicaApi: Musica? = null

    //Instancia do FirebaseStorage - Conexao
    //val firebaseStorage = FirebaseStorage.getInstance()
    //val firebaseStore = FirebaseFirestore.getInstance()
    private lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var storageReference: StorageReference
    lateinit var firebaseStorage: FirebaseStorage


    fun setupRecycleView(
        recycleView: RecyclerView, context: Context
    ){
        firebaseFirestore = FirebaseFirestore.getInstance()
        //progressBar.visibility = View.VISIBLE
        val collection = firebaseFirestore.collection("music")

        val task = collection.get()

        //val task = collection.limit(10).get() // 10 primeiros itens da consulta
        task.addOnSuccessListener {
            if (it != null){
                //pega resultado da consulta
                Toast.makeText(context, "Criando Lista", Toast.LENGTH_LONG).show()

                val musicas = it.toObjects(Musica::class.java)
                //Toast.makeText(context, musicas.size, Toast.LENGTH_LONG).show()
                //alimentando a recycle
                recycleView.adapter = ListaMusicaAdapter(musicas, this::callbacListaMusicas)
                recycleView.layoutManager = LinearLayoutManager(context)

            }else{
                Toast.makeText(context, "Lista vazia", Toast.LENGTH_LONG).show()
            }
          //  progressBar.visibility = View.GONE
        }.addOnFailureListener{
          //  progressBar.visibility = View.GONE
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
    }

    fun callbacListaMusicas(musica: Musica, context: Context){
        val myDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_musica_api, null)
        val myBuilder = AlertDialog.Builder(context)
            .setView(myDialogView)
            .setTitle("Informações Detalhadas")

        val myAlertDialog = myBuilder.show()
        //Aqui vc chama o método de busca da API passando o nome da musica e o artista
        informarcoesMusica(musica.nomeMusica!!.toLowerCase(), musica.artista!!.toLowerCase())
        //Essa informação veio quando o usuário clicou em cima da musica lá na recyclerView
        //metodoBuscaAPI(musica.artista,musica.nomeMusica)
        //Depois preenche os textViews do AlertDialog

        myDialogView.textViewArtista.setText(musicaApi!!.track!!.artist!!.name)
        myDialogView.textViewMusica.setText(musicaApi!!.track!!.title)
        myDialogView.textViewTempoTotal.setText(musicaApi!!.track!!.duration.toString())
        myDialogView.textViewAlbum.setText(musicaApi!!.track!!.album!!.title)

        myDialogView.btnVoltar.setOnClickListener {
            //Botão para fechar o dialog, pode ser um daqueles floatbutton com um x em lá em cima
            myAlertDialog.dismiss()
        }
    }

    fun informarcoesMusica(nomeMusica: String, artista: String){

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.deezer.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val musicaService = retrofit.create(MusicaService::class.java)
        val task = musicaService.buscaMusica(nomeMusica, artista)

        task.enqueue(object : Callback<Musica>{

            override fun onResponse(call: Call<Musica>, response: Response<Musica>) {
                musicaApi = response.body()

            }

            override fun onFailure(call: Call<Musica>, t: Throwable) {
                Log.e("Musica", t.message!!)
            }
        })

    }

}
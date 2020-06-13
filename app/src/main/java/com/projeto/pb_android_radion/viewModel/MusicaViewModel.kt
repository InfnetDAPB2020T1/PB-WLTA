package com.projeto.pb_android_radion.viewModel

import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.projeto.pb_android_radion.R
import com.projeto.pb_android_radion.adapter.ListaMusicaAdapter
import com.projeto.pb_android_radion.apiService.ApiClient
import com.projeto.pb_android_radion.model.MusicList
import com.projeto.pb_android_radion.model.Musica
import kotlinx.android.synthetic.main.dialog_musica_api.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL


class MusicaViewModel :  ViewModel() {

    var musicaApi: Musica? = null

    //Instancia do FirebaseStorage - Conexao
    //val firebaseStorage = FirebaseStorage.getInstance()
    //val firebaseStore = FirebaseFirestore.getInstance()
    private lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var storageReference: StorageReference
    lateinit var firebaseStorage: FirebaseStorage


    fun setupRecycleView(
        recycleView: RecyclerView, context: Context, progressBar: ProgressBar
    ){
        firebaseFirestore = FirebaseFirestore.getInstance()
        //progressBar.visibility = View.VISIBLE
        val collection = firebaseFirestore.collection("music")

        val task = collection.get()

        //val task = collection.limit(10).get() // 10 primeiros itens da consulta
        task.addOnSuccessListener {
            if (it != null){
                //pega resultado da consulta
//                Toast.makeText(context, "Criando Lista", Toast.LENGTH_LONG).show()

                val musicas = it.toObjects(Musica::class.java)
                //Toast.makeText(context, musicas.size, Toast.LENGTH_LONG).show()
                //alimentando a recycle
                progressBar.visibility = View.GONE
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
        //Essa informação veio quando o usuário clicou em cima da musica lá na recyclerView
        //metodoBuscaAPI(musica.artista,musica.nomeMusica)
        //Depois preenche os textViews do AlertDialog

        ApiClient.getMusicasService()
            .show()
//        musica.artista.toString(), musica.nomeMusica.toString()
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

//                    var imageView: ImageView = (R.drawable.ic_home) as ImageView

//                    val url =
//                        URL(lista!!.data[0].album?.cover_medium)
//                    val bmp =
//                        BitmapFactory.decodeStream(url.openConnection().getInputStream())
//                    myDialogView.imgViewFotoAlbum.setImageBitmap(bmp)
//                    val a : Bitmap = lista!!.data[0].album?.cover_medium as Bitmap
//                    Picasso.get().load(lista!!.data[0].album?.cover_medium).into(imageView)
//                    myDialogView.imgViewFotoAlbum.setImageBitmap(a)
                    myDialogView.textViewArtista.setText(lista!!.data[0].artist?.name)
                    myDialogView.textViewMusica.setText(lista!!.data[0].title)
                    myDialogView.textViewTempoTotal.setText(" ${lista!!.data[0].duration} segundos")
                    myDialogView.textViewAlbum.setText(lista!!.data[0].album?.title)

                }

            })

//        myDialogView.textViewArtista.setText(musicaApi!!.track!!.artist!!.name)
//        myDialogView.textViewMusica.setText(musicaApi!!.track!!.title)
//        myDialogView.textViewTempoTotal.setText(musicaApi!!.track!!.duration.toString())
//        myDialogView.textViewAlbum.setText(musicaApi!!.track!!.album!!.title)

        myDialogView.btnVoltar.setOnClickListener {
            //Botão para fechar o dialog, pode ser um daqueles floatbutton com um x em lá em cima
            myAlertDialog.dismiss()
        }
    }

//    fun informarcoesMusica(nomeMusica: String, artista: String){
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.deezer.com")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val musicaService = retrofit.create(MusicaService::class.java)
//        val task = musicaService.buscaMusica(nomeMusica, artista)
//
//        task.enqueue(object : Callback<Musica> {
//
//            override fun onResponse(call: Call<Musica>, response: Response<Musica>) {
//                musicaApi = response.body()
//
//            }
//
//            override fun onFailure(call: Call<Musica>, t: Throwable) {
//                Log.e("Musica", t.message!!)
//            }
//        })
//
//    }

}


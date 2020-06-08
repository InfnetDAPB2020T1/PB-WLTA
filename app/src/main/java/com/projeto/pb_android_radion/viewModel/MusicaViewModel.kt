package com.projeto.pb_android_radion.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projeto.pb_android_radion.adapter.ListaMusicaAdapter
import com.projeto.pb_android_radion.model.Musica
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class MusicaViewModel :  ViewModel() {

    var musica: Musica? = null

    //Instancia do FirebaseStorage - Conexao
  //  val firebaseStorage = FirebaseStorage.getInstance()
//    val firebaseStore = FirebaseFirestore.getInstance()
    private lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var storageReference: StorageReference
    lateinit var firebaseStorage: FirebaseStorage


    fun setupRecycleView(
        recycleView: RecyclerView, context: Context
    ){
        firebaseFirestore = FirebaseFirestore.getInstance()
       // progressBar.visibility = View.VISIBLE
        val collection = firebaseFirestore.collection("music")

        val task = collection.get()

//        val task = collection.limit(10).get() // 10 primeiros itens da consulta
        task.addOnSuccessListener {
            if (it != null){
                //pega resultado da consulta
                Toast.makeText(context, "Criando Lista", Toast.LENGTH_LONG).show()

                val musicas = it.toObjects(Musica::class.java)
//                Toast.makeText(context, musicas.size, Toast.LENGTH_LONG).show()
                //alimentando a recycle
                recycleView.adapter = ListaMusicaAdapter(musicas)
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

}

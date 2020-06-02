package com.projeto.pb_android_radion.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.projeto.pb_android_radion.adapter.ListaMusicaAdapter
import com.projeto.pb_android_radion.model.Musica

class MusicaViewModel :  ViewModel() {

    var musica: Musica? = null

    //Instancia do FirebaseStorage - Conexao
  //  val firebaseStorage = FirebaseStorage.getInstance()
    val firebaseStore = FirebaseFirestore.getInstance()
    lateinit var storageReference: StorageReference
    lateinit var firebaseStorage: FirebaseStorage


    fun setupRecycleView(
        recycleView: RecyclerView, context: Context
    ){
       // progressBar.visibility = View.VISIBLE
        val collection = firebaseStore.collection("musica")
        //val task = collection.get()

        val task = collection.limit(10).get() // 10 primeiros itens da consulta
        task.addOnSuccessListener {
            if (it!= null){
                //pega resultado da consulta

                val musica = it.toObjects(Musica::class.java)
                //alimentando a recycle
                recycleView.adapter = ListaMusicaAdapter(musica)
                recycleView.layoutManager = LinearLayoutManager(context)
            }
          //  progressBar.visibility = View.GONE
        }.addOnFailureListener{
          //  progressBar.visibility = View.GONE
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
    }

}

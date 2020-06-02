package com.projeto.pb_android_radion.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.projeto.pb_android_radion.model.Search


class SearchViewModel : ViewModel(){

    var search: Search? = null
    val firebaseStore = FirebaseFirestore.getInstance()
    lateinit var storageReference: StorageReference
    lateinit var firebaseStorage: FirebaseStorage

    fun setupRecycleView(
        recycleView: RecyclerView, context: Context
    ){
        // progressBar.visibility = View.VISIBLE
        val collection = firebaseStore.collection("listaMusicas")
        collection.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null){
                Log.e("Firestore", firebaseFirestoreException.message)
            } else {
                if (querySnapshot != null){
                    val listaMusicas = querySnapshot.toObjects(SearchViewModel::class.java)
                 //   recycleView.adapter = SearchAdapter(listaMusicas)
                    recycleView.layoutManager = LinearLayoutManager(context)

                }
            }
        }
    }
}
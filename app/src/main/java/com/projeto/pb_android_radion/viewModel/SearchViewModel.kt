package com.example.pb_android_radion.viewModel

import android.content.Context
<<<<<<< HEAD:app/src/main/java/com.projeto.pb_android_radion/viewModel/SearchViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
=======
import android.widget.Toast
import com.example.pb_android_radion.adapter.SearchAdapter
import com.example.pb_android_radion.model.Search
>>>>>>> parent of 6a6e5f8... Mudando search:app/src/main/java/com/example/pb_android_radion/viewModel/SearchViewModel.kt
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class SearchViewModel : ViewModel(){

    var search: Search? = null
    val firebaseStore = FirebaseFirestore.getInstance()
    lateinit var storageReference: StorageReference
    lateinit var firebaseStorage: FirebaseStorage

    fun setupRecycleView(
        recycleView: RecyclerView, context: Context
    ){
        // progressBar.visibility = View.VISIBLE
        val collection = firebaseStore.collection("musicas")
        val task = collection.get()
        task.addOnSuccessListener {
            if (it!= null){
                val search = it.toObjects(Search::class.java)
                //alimentando a recycle
                recycleView.adapter = SearchAdapter(search)
                recycleView.layoutManager = LinearLayoutManager(context)
            }
            //  progressBar.visibility = View.GONE
        }.addOnFailureListener{
            //  progressBar.visibility = View.GONE
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
    }

}
package com.projeto.pb_android_radion.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import java.io.Serializable

class Musica(
    var nomeMusica: String,
    var nomeCantor: String
  //  var classificacao: Int = 0,
   /* @PrimaryKey(autoGenerate = true)
    var id: Int? = null*/

)
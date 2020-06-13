package com.projeto.pb_android_radion.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import java.io.Serializable

class Musica(

    var track: Track? = null,
    var nomeMusica: String? = null,
    var artista: String? = null

    //var musicaUrl: String? = null,
    //var musica: String? = null
    //var classificacao: Int = 0,
    /*@PrimaryKey(autoGenerate = true)
    var id: Int? = null*/

)
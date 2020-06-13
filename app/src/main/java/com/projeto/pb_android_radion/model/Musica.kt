package com.projeto.pb_android_radion.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import com.google.gson.annotations.SerializedName
import com.projeto.pb_android_radion.apiService.model.Album
import com.projeto.pb_android_radion.apiService.model.Artist
import java.io.Serializable

class Musica(

    @SerializedName("data")
    var musics: MutableList<Musica>? = null,

    var track: Track? = null,
    var nomeMusica: String? = null,
    var artista: String? = null,
    var title: String? = null,
    var duration: String? =null,
    var artist: Artist? = null,
    var album: Album? = null

    //var musicaUrl: String? = null,
    //var musica: String? = null
    //var classificacao: Int = 0,
    /*@PrimaryKey(autoGenerate = true)
    var id: Int? = null*/

)
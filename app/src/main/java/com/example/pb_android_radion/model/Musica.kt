package com.example.pb_android_radion.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "musicas")
class Musica(
    var nomeMusica: String,
    var nomeCantor: String,
  //  var classificacao: Int = 0,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

): Serializable {
}
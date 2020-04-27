package com.example.pb_android_radion.Dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MusicaDAO {

    @Query("SELECT * FROM musicas WHERE nomeMusica = :nomeMusica")
    fun buscarMusica(nomeMusica: String)

    @Query("SELECT * FROM musicas WHERE nomeCantor = :nomeCantor")
    fun buscarCantor(nomeCantor: String)
}
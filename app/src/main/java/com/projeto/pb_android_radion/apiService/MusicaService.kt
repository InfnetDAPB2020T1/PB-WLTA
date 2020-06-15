package com.projeto.pb_android_radion.apiService

import com.projeto.pb_android_radion.model.MusicList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MusicaService {

    //https://api.deezer.com/search?q=eminem
    //search/track?order=RANKING&q=artist:"eminem"track:"monster"
    //search?q={nomeMusica}&q={artista}

    //@Query("search/track?order=RANKING&q=artist:{artista}track:{nomeMusica}")

    //Mocado
//    @GET("search?q=artist:'capital inicial' track:'fogo'")
//    fun show() : Call<MusicList>

    @GET("search?q=")
    fun show(@Query("q") artista_musica: String) : Call<MusicList>

}
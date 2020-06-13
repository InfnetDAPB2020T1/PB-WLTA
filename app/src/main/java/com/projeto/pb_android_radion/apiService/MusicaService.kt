package com.projeto.pb_android_radion.apiService

import com.projeto.pb_android_radion.model.Musica
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicaService {

    //https://api.deezer.com/search?q=eminem
    //search/track?order=RANKING&q=artist:"eminem"track:"monster"
    //search?q={nomeMusica}&q={artista}

    //@Query("search/track?order=RANKING&q=artist:{artista}track:{nomeMusica}")

    @GET("search?q=artist:'{artista}'track:'{musica}'")
    fun show(@Path("artista") artista: String,
             @Path("musica") musica: String) : Call<List<Musica>>
}
package com.projeto.pb_android_radion.service

import com.projeto.pb_android_radion.model.Musica
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicaService {

    //https://api.deezer.com/search?q=eminem
    //search/track?order=RANKING&q=artist:"eminem"track:"monster"
    //search?q={nomeMusica}&q={artista}

    //@Query("search/track?order=RANKING&q=artist:{artista}track:{nomeMusica}")

    @GET("/search/q=track:{nomeMusica}artist:{artista}")
    fun buscaMusica(@Path("nomeMusica")nomeMusica: String,
                    @Path("artista")artista: String) : Call<Musica>
}
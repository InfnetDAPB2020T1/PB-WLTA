package com.projeto.pb_android_radion.apiService


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var instance : Retrofit? = null
    private val url = "https://api.deezer.com/"
    private fun getInstance(): Retrofit{
        if (instance == null){
            instance = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance as Retrofit
    }

    fun getMusicasService() : MusicaService = getInstance().create(MusicaService::class.java)

}
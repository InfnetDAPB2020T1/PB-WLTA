package com.projeto.pb_android_radion.model

import com.google.gson.annotations.SerializedName

class MusicList {
    @SerializedName("data")
    private var musics: MutableList<Musica>? = null
}
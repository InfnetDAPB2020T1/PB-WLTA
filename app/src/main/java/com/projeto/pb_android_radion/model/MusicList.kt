package com.projeto.pb_android_radion.model

import com.google.gson.annotations.SerializedName
import com.projeto.pb_android_radion.apiService.model.Album
import com.projeto.pb_android_radion.apiService.model.Artist

class MusicList(
    @SerializedName("data")
    var musics: List<Musica>? = null,
    var title: String? = null,
    var duration: String? =null,
    var artist: Artist? = null,
    var album: Album? = null
) {


}
package com.projeto.pb_android_radion.model

class MusicList(
    val data: List<Atributos>)
{

    class Atributos(
        val title: String? = null,
        val artist: Artist? = null,
        val album: Album? = null,
        val duration: String? = null
    ) {
        class Artist (
            val name: String? = null,
            val link: String? = null,
            val type: String? = null
        )

        class Album(
            val cover_medium: String? = null,
            val title: String? = null
        )
    }
}
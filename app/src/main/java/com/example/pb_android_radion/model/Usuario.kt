package com.example.pb_android_radion.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pb_android_radion.throws.UsuarioDDDException
import com.example.pb_android_radion.throws.UsuarioTelefoneException
import java.io.Serializable

class Usuario(

    var apelido: String,
    var imagem: String? = null,
    var email: String,
    var senha: String,
    var nomeCompleto: String,
    var estado: String,
    var ddd: String,
    var telefone: String,
    var id: Int? = null

) : Serializable {

    init {
        if(ddd.length != 2)
            throw UsuarioDDDException()
        if(telefone.length != 9)
            throw UsuarioTelefoneException()
    }
}
package com.example.pb_android_radion.model

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
    var telefone: String

)
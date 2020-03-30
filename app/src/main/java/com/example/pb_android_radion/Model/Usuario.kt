package com.example.pb_android_radion.Model

import java.io.Serializable

class Usuario(
    var apelido: String,
    var email: String,
    var senha: String,
    var nome: String,
    var sobrenome: String,
    var cpf: String,
    var estado: String,
    var ddd: String,
    var telefone: String

) : Serializable {
}
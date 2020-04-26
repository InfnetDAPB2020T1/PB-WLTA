package com.example.pb_android_radion.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pb_android_radion.Throws.UsuarioCPFException
import com.example.pb_android_radion.Throws.UsuarioDDDException
import com.example.pb_android_radion.Throws.UsuarioTelefoneException
import java.io.Serializable

@Entity(tableName = "usuarios")
class Usuario(
    var apelido: String,
    var email: String,
    var senha: String,
    var nome: String,
    var sobrenome: String,
    var cpf: String,
    var estado: String,
    var ddd: String,
    var telefone: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

) : Serializable {

    init {
        if(ddd.length != 2)
            throw UsuarioDDDException()
        if(telefone.length != 9)
            throw UsuarioTelefoneException()
        if(cpf.length != 11)
            throw UsuarioCPFException()

    }
}
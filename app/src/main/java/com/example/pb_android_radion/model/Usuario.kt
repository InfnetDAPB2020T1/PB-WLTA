package com.example.pb_android_radion.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pb_android_radion.throws.UsuarioCPFException
import com.example.pb_android_radion.throws.UsuarioDDDException
import com.example.pb_android_radion.throws.UsuarioTelefoneException
import java.io.Serializable

@Entity(tableName = "usuarios")
class Usuario(
    var apelido: String? = null,
    var email: String? = null,
    var senha: String? = null,
    var nome: String? = null,
    var sobrenome: String? = null,
    var cpf: String? = null,
    var estado: String? = null,
    var ddd: String? = null,
    var telefone: String? = null,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

) : Serializable {

    init {
        if(ddd!!.length != 2)
            throw UsuarioDDDException()
        if(telefone!!.length != 9)
            throw UsuarioTelefoneException()
        if(cpf!!.length != 11)
            throw UsuarioCPFException()
    }
}
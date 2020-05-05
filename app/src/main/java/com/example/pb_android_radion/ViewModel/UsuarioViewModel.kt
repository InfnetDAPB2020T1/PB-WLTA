package com.example.pb_android_radion.ViewModel

import androidx.lifecycle.ViewModel
import com.example.pb_android_radion.Model.Musica
import com.example.pb_android_radion.Model.Usuario
import com.example.pb_android_radion.Model.Usuarios
import java.util.*

class UsuarioViewModel: ViewModel() {
    var email: String? = null
    var senha: String? = null
    var nome: String? = null
    var sobrenome: String? = null
    var cpf: String? = null
    var apelido: String? = null
    var estado: String? = null
    var telefone: String? = null
    var ddd: String? = null

    var usuario: Usuario? = null
    var usuarioLogado: Usuario? = null
    var usuarios : MutableList<Usuario> = mutableListOf()
    var listaUsuariosSeriazable: Usuarios? = Usuarios()
    var usuarioLogadoApelido: String = ""
    var usuarioLogadoNome: String = ""

//    private val Usuarios = ArrayList<Usuario>()
//
//    fun addUsuario(usuario: Usuario) {
//        Usuarios.add(usuario)
//    }

    fun cadastro(apelido: String, email: String, senha: String){
        usuario!!.apelido = apelido
        usuario!!.email = email
        usuario!!.senha = senha
    }

    fun complementoCadastro(nome: String, sobrenome: String, cpf: String, estado: String,
                            ddd: String, telefone: String){

        usuario!!.nome = nome
        usuario!!.sobrenome = sobrenome
        usuario!!.cpf = cpf
        usuario!!.estado = estado
        usuario!!.ddd = ddd
        usuario!!.telefone = telefone

    }
}
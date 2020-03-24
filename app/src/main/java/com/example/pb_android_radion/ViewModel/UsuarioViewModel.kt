package com.example.pb_android_radion.ViewModel

import androidx.lifecycle.ViewModel
import com.example.pb_android_radion.Model.Usuario
import java.util.*

class UsuarioViewModel: ViewModel() {
    var usuario: Usuario? = null


    private val Usuarios = ArrayList<Usuario>()

    fun addUsuario(usuario: Usuario) {
        Usuarios.add(usuario)
    }


}
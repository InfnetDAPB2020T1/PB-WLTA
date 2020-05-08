package com.example.pb_android_radion.ViewModel

import android.app.Application
import android.os.AsyncTask
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.pb_android_radion.Model.Usuario
import com.example.pb_android_radion.Model.Usuarios
import com.example.pb_android_radion.Service.AppDatabaseService

class UsuarioViewModel: ViewModel() {

    //var db = AppDatabaseService.getInstance(Application().applicationContext)

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

    /*inner class OperacaoCriarUsuarioTask : AsyncTask<Usuario, Unit, Unit>(){
        override fun doInBackground(vararg params: Usuario?) {

            //db.usuarioDao().criarUsuario(params[0]!!)
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)

            Toast.makeText(Application().applicationContext,
                "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show()
        }
    }*/
}
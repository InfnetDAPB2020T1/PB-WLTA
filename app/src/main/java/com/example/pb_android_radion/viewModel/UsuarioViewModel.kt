package com.example.pb_android_radion.viewModel

import androidx.lifecycle.ViewModel
import com.example.pb_android_radion.model.Usuario
import android.app.Application
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.example.pb_android_radion.model.Usuarios
import com.example.pb_android_radion.service.AppDatabaseService

class UsuarioViewModel: ViewModel() {

    //var db = AppDatabaseService.getInstance()

    var usuario: Usuario? = null
    var usuarioLogado: Usuario? = null

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

            db.usuarioDao().criarUsuario(params[0]!!)
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)

            Toast.makeText(Application().applicationContext,
                "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show()
        }
    }*/
}
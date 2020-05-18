package com.example.pb_android_radion.viewModel

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.pb_android_radion.R
import com.example.pb_android_radion.model.Usuario
import com.example.pb_android_radion.service.AppDatabaseService
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.layout_cadastro.*
import kotlinx.android.synthetic.main.layout_cadastro.view.*


class UsuarioViewModel: ViewModel() {

    var usuario: Usuario? = null
    var usuarioLogado: Usuario? = null

    fun salvarNoBanco( context: Context) {

        val novoUsuario = Usuario(
            usuario!!.apelido,
            usuario!!.imagem,
            usuario!!.email,
            usuario!!.senha,
            usuario!!.nome,
            //  usuarioViewModel.usuario!!.cpf,
            usuario!!.estado,
            usuario!!.ddd,
            usuario!!.telefone

        )
        var db = AppDatabaseService.getInstance(context)
        db.usuarioDao().criarUsuario(novoUsuario)
    }

    fun verificarNulo(
        view: View, context: Context): Boolean {
        //Verifico se algum campo está nulo ou vazio
        if (
            view.boxNomeCadastro.text.isNullOrBlank() ||
            view.boxApelidoCadastro.text.isNullOrBlank() ||
            view.boxEmailCadastro.text.isNullOrBlank() ||
            view.boxEstadoCadastro.text.isNullOrBlank() ||
            view.boxDDDCadastro.text.isNullOrBlank() ||
            view.boxTelefoneCadastro.text.isNullOrBlank()
        ) {
            return false
        } else {
            //Caso tudo ocorra ok, começo a alimentar o view model com o resto das informações
            usuario = Usuario(
                apelido = view.boxApelidoCadastro.text.toString(),
                imagem = view.imageViewPerfil.toString(),
                email = view.boxEmailCadastro.text.toString(),
                senha = view.boxSenhaCadastro2.text.toString(),
                nome = view.boxNomeCadastro.text.toString(),
                //  cpf = boxCpf.text.toString(),
                estado = view.boxEstadoCadastro.text.toString(),
                ddd = view.boxDDDCadastro.text.toString(),
                telefone = view.boxTelefoneCadastro.text.toString()
            )
            return true
        }
    }
}
package com.example.pb_android_radion.fragment

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.pb_android_radion.R
import com.example.pb_android_radion.service.AppDatabaseService
import com.example.pb_android_radion.viewModel.UsuarioViewModel
import kotlinx.android.synthetic.main.fragment_perfil.*

class PerfilFragment : Fragment() {
    private lateinit var usuarioViewModel: UsuarioViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            usuarioViewModel = ViewModelProviders.of(it).get(UsuarioViewModel::class.java)
        }

        //OperacaoBancoTask().execute()
        textViewNomeSobrenomePerfil.text = usuarioViewModel.usuarioLogado!!.nome
        textViewApelidoPerfil.text = usuarioViewModel.usuarioLogado!!.apelido
        textViewCPFUsuario.text = usuarioViewModel.usuarioLogado!!.cpf
        textViewEmailUsuario.text = usuarioViewModel.usuarioLogado!!.email
        textViewTelefoneUsuario.text = usuarioViewModel.usuarioLogado!!.telefone
        textViewEstadoUsuario.text = usuarioViewModel.usuarioLogado!!.estado
    }

    /*inner class OperacaoBancoTask : AsyncTask<Unit, Unit, Array<Usuario>>() {

        override fun doInBackground(vararg params: Unit?): Array<Usuario> {
            var db = AppDatabaseService.getInstance(activity!!.baseContext)

            return db.usuarioDao().listarUsuarios()
        }

        override fun onPostExecute(result: Array<Usuario>?) {
            super.onPostExecute(result)

            textViewNomeSobrenomePerfil.text = usuarioViewModel.usuarioLogado.nome
            textViewApelidoPerfil.text = usuarioViewModel.usuarioLogado.apelido
            textViewCPFUsuario.text = usuarioViewModel.usuarioLogado.cpf
            textViewEmailUsuario.text = usuarioViewModel.usuarioLogado.email
            textViewTelefoneUsuario.text = usuarioViewModel.usuarioLogado.telefone
            textViewEstadoUsuario.text = usuarioViewModel.usuarioLogado.estado
            // Botão drawable para trocar senha chamando um card de dialogo
            TextViewSair// sair da aplicação
        }
    }*/
}
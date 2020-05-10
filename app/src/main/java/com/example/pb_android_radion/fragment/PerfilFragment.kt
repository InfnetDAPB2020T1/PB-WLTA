package com.example.pb_android_radion.fragment

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.pb_android_radion.MainActivity
import com.example.pb_android_radion.R
import com.example.pb_android_radion.model.Usuario
import com.example.pb_android_radion.service.AppDatabaseService
import com.example.pb_android_radion.viewModel.UsuarioViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_perfil.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*

/**
 * A simple [Fragment] subclass.
 */
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

        OperacaoBancoTask().execute()
        //textViewApelidoPerfil.text = usuarioViewModel.usuario!!.apelido
        // textViewNomeSobrenomePerfil.text = usuarioViewModel.usuario!!.nome
    }

    inner class OperacaoBancoTask : AsyncTask<Unit, Unit, Array<Usuario>>() {

        override fun doInBackground(vararg params: Unit?): Array<Usuario> {
            var db = AppDatabaseService.getInstance(activity!!.baseContext)

            return db.usuarioDao().listarUsuarios()
        }

        override fun onPostExecute(result: Array<Usuario>?) {
            super.onPostExecute(result)

          /*  Log.i("Usuario", "${usuarioViewModel.usuarioLogado!!.nome}," +
                           "${usuarioViewModel.usuarioLogado!!.senha}," +
                           "${usuarioViewModel.usuarioLogado!!.apelido}," +
                           "${usuarioViewModel.usuarioLogado!!.nome}," +
                           "${usuarioViewModel.usuarioLogado!!.cpf}," +
                           "${usuarioViewModel.usuarioLogado!!.estado}," +
                           "${usuarioViewModel.usuarioLogado!!.ddd}," +
                           "${usuarioViewModel.usuarioLogado!!.telefone}")*/

            textViewNomeSobrenomePerfil.text = "//usuarioViewModel.usuarioLogado!!.nome"
            textViewApelidoPerfil.text = "Arlequina"
            textViewCPFUsuario.text = "Harley Queen"
            textViewEmailUsuario.text = "Harley Queen"
            textViewTelefoneUsuario.text = "Harley Queen"
            textViewEstadoUsuario.text = "Harley Queen"
            // Botão drawable para trocar senha chamando um card de dialogo
            TextViewSair// sair da aplicação
        }
    }
}
package com.example.pb_android_radion.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.projeto.pb_android_radion.R
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

        usuarioViewModel.infoPerfil(
            textViewNomeSobrenomePerfil,
            textViewApelidoPerfil,
            textViewEstadoUsuario,
            textViewTelefoneUsuario,
            textViewEmailUsuario
        )

        // Botão drawable para trocar senha chamando um card de dialogo
        //TextViewSair// sair da aplicação*/
    }
}
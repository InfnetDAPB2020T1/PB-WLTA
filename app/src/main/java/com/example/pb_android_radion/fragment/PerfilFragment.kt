package com.example.pb_android_radion.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        var nomeCompleto = usuarioViewModel.usuarioLogado!!.nome +
                usuarioViewModel.usuarioLogado!!.nome

        textViewNomeSobrenome.text = nomeCompleto
        textViewApelidoUsuario.text = usuarioViewModel.usuarioLogado!!.apelido
        textViewCPFUsuario.text = usuarioViewModel.usuarioLogado!!.cpf
        textViewEmailUsuario.text = usuarioViewModel.usuarioLogado!!.email
        textViewTelefoneUsuario.text = usuarioViewModel.usuarioLogado!!.telefone
        textViewEstadoUsuario.text = usuarioViewModel.usuarioLogado!!.estado
        textViewSenhaUsuario.text = usuarioViewModel.usuarioLogado!!.senha

        var db = AppDatabaseService.getInstance(requireContext().applicationContext)

        imageViewEditarApelido.setOnClickListener {
            db.usuarioDao().mudarApelido("Boladao123",
                usuarioViewModel.usuarioLogado!!.id)
            textViewApelidoUsuario.text = usuarioViewModel.usuarioLogado!!.apelido
        }

        imageViewEditarEstado.setOnClickListener {
            db.usuarioDao().mudarEstado("BH", usuarioViewModel.usuarioLogado!!.id)
            textViewEstadoUsuario.text = usuarioViewModel.usuarioLogado!!.estado
        }

        imageViewEditarSenha.setOnClickListener {
            db.usuarioDao().mudarSenha("54321", usuarioViewModel.usuarioLogado!!.id)
            textViewSenhaUsuario.text = usuarioViewModel.usuarioLogado!!.senha
        }

        imageViewSair.setOnClickListener {
            Toast.makeText(requireActivity().baseContext, "Saindo",
                Toast.LENGTH_SHORT).show()
        }
    }
}

package com.example.pb_android_radion.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.pb_android_radion.Model.Usuario

import com.example.pb_android_radion.R
import com.example.pb_android_radion.ViewModel.UsuarioViewModel
import kotlinx.android.synthetic.main.fragment_cadastro.*
import kotlinx.android.synthetic.main.fragment_complemento_cadastro.*

/**
 * A simple [Fragment] subclass.
 */
class ComplementoCadastroFragment : Fragment() {

    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complemento_cadastro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            usuarioViewModel = ViewModelProviders.of(it).get(UsuarioViewModel::class.java)
        }

        btnFinalizarCadastro.setOnClickListener{
            //Verifico se algum campo está nulo ou vazio
            if(boxNomeCadastro.text.isNullOrEmpty() || boxSobrenomeCadastro.text.isNullOrEmpty() || boxEstadoCadastro.text.isNullOrEmpty()
                || boxCpf.text.isNullOrEmpty() || boxDDDCadastro.text.isNullOrEmpty() || boxTelefoneCadastro.text.isNullOrEmpty()){
                Toast.makeText(activity, "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show()
            }else{
                //Caso tudo ocorra ok, começo a alimentar o view model com o resto das informações
                usuarioViewModel.nome = boxNomeCadastro.text.toString()
                usuarioViewModel.sobrenome = boxSobrenomeCadastro.text.toString()
                usuarioViewModel.estado = boxEstadoCadastro.text.toString()
                usuarioViewModel.cpf = boxCpf.text.toString()
                usuarioViewModel.nome = boxNomeCadastro.text.toString()
                usuarioViewModel.nome = boxNomeCadastro.text.toString()

                //Cria novo Usuário
                val novoUsuario = Usuario(
                    usuarioViewModel.apelido.toString(),
                    usuarioViewModel.email.toString(),
                    usuarioViewModel.senha.toString(),
                    usuarioViewModel.nome.toString(),
                    usuarioViewModel.sobrenome.toString(),
                    usuarioViewModel.cpf.toString(),
                    usuarioViewModel.estado.toString(),
                    usuarioViewModel.ddd.toString(),
                    usuarioViewModel.telefone.toString()
                )

                usuarioViewModel.usuario == novoUsuario
                //add usuário na lista de Usuarios
                usuarioViewModel.listaUsuariosSeriazable?.lista?.add(novoUsuario)
                usuarioViewModel.usuarios.add(novoUsuario)

                findNavController().navigate(R.id.returnToLogin)
            }

        }
    }

}

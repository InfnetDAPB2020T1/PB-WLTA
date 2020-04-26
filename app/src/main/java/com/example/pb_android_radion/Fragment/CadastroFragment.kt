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


class CadastroFragment : Fragment() {

    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            usuarioViewModel = ViewModelProviders.of(it).get(UsuarioViewModel::class.java)
        }


        btnContinuar.setOnClickListener{
            //Verifico se algum campo está nulo ou vazio
            if(boxApelidoCadastro.text.isNullOrEmpty() || boxEmailCadastro.text.isNullOrEmpty() || boxSenhaCadastro.text.isNullOrEmpty()){
                Toast.makeText(activity, "Por favor preencha todos os campos", Toast.LENGTH_SHORT).show()
            }else{
                //Começo a implementar na viewModel as informações
                criarCadastro(boxApelidoCadastro.text.toString(), boxEmailCadastro.text.toString(),
                    boxSenhaCadastro.text.toString())

                findNavController().navigate(R.id.cadastroToComplementoCadastro)
            }
        }
    }

    private fun criarCadastro(apelido: String, email:String, senha: String){
        usuarioViewModel.apelido = apelido
        usuarioViewModel.email = email
        usuarioViewModel.senha = senha
    }
}

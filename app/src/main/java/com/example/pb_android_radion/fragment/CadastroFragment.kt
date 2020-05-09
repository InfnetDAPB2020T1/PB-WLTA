package com.example.pb_android_radion.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.pb_android_radion.LoginActivity
import com.example.pb_android_radion.MainActivity
import com.example.pb_android_radion.R
import com.example.pb_android_radion.viewModel.UsuarioViewModel
import kotlinx.android.synthetic.main.fragment_cadastro.*
import kotlinx.android.synthetic.main.layout_cadastro.*

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



    }
/* btnCadastrarUsuario.setOnClickListener{
            //Verifico se algum campo está nulo ou vazio
          //  verificarNulo()
            //findNavController().navigate(R.id.cadastroToComplementoCadastro)
            startActivity(Intent(activity?.baseContext, LoginActivity::class.java))
        }
    private fun verificarNulo(){
        if(boxApelidoCadastro.text.isNullOrEmpty() || boxEmailCadastro.text.isNullOrEmpty()
            || boxSenhaCadastro.text.isNullOrEmpty()){
            Toast.makeText(activity?.baseContext, "Por favor preencha todos os campos",
                Toast.LENGTH_SHORT).show()
        }else{

            //Começo a implementar na viewModel as informações
            usuarioViewModel.apelido = boxApelidoCadastro.text.toString()
            usuarioViewModel.email = boxEmailCadastro.text.toString()
            usuarioViewModel.senha = boxSenhaCadastro.text.toString()

            /*informacoesNoViewModel(boxApelidoCadastro.text.toString(),
                boxEmailCadastro.text.toString(), boxSenhaCadastro.text.toString())*/
        }
    }

    /*private fun informacoesNoViewModel(apelido: String, email:String, senha: String) {
        usuarioViewModel.cadastro(apelido, email, senha)
    }*/*/
}

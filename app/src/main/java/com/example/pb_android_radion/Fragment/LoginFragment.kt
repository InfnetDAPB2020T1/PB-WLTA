package com.example.pb_android_radion.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.pb_android_radion.MainActivity
import com.example.pb_android_radion.R
import com.example.pb_android_radion.ViewModel.UsuarioViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            usuarioViewModel = ViewModelProviders.of(it).get(UsuarioViewModel::class.java)
        }

        textoCadastroLogin.setOnClickListener{
            findNavController().navigate(R.id.LoginToCadastrar)
        }

        val listaUsuarios = usuarioViewModel.listaUsuariosSeriazable
        btnLogarLogin.setOnClickListener {
            //Variavel para ver se o usuário digitado na tela existe ao percorrer a lista de Usuários
            var existe: Boolean = false
            listaUsuarios!!.lista.forEach{
                if(boxEmailLogin.text.toString().compareTo(it.email) ==0 && boxSenhaLogin.text.toString() == it.senha){
                    existe = true
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("usuario", it)
                    intent.putExtra("listaUsuarios", listaUsuarios)
                    startActivity(intent)

                }
            }
            if(!existe){
                Toast.makeText(this.context, "Usuário inválido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

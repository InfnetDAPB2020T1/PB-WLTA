package com.example.pb_android_radion.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.pb_android_radion.MainActivity
import com.example.pb_android_radion.R
import com.example.pb_android_radion.viewModel.UsuarioViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var usuarioViewModel: UsuarioViewModel
    private lateinit var firebaseAuth: FirebaseAuth
    //private lateinit var usuarioLogado: FirebaseUser

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

        btnLogarLogin.setOnClickListener {
            if(boxEmailLogin.text.toString().isBlank() || boxSenhaLogin.text.toString().isBlank()){
                Toast.makeText(this.context, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }else{
                loginFirestore(boxEmailLogin.text.toString(), boxSenhaLogin.text.toString())
            }
        }
    }

    fun loginFirestore(boxEmail: String, boxSenha: String){

        firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signInWithEmailAndPassword(boxEmail, boxSenha)
            .addOnSuccessListener {
                if(it != null){
                    startActivity(Intent(activity?.baseContext, MainActivity::class.java))

                    Toast.makeText(context, "Bem vindo ${it.user!!.email}",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                if(it.message == "The email address is baldy formatted"){
                    Toast.makeText(context, "Por favor insira um email com formato válido",
                        Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Email ou senha inválidos!",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}

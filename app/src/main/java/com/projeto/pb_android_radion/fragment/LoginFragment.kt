package com.projeto.pb_android_radion.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.projeto.pb_android_radion.R
import com.projeto.pb_android_radion.viewModel.UsuarioViewModel
import com.google.firebase.auth.FirebaseUser
import com.projeto.pb_android_radion.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var usuarioViewModel: UsuarioViewModel
    private lateinit var usuarioLogado: FirebaseUser


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

        val auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null){

            val intt = Intent(this.context, MainActivity::class.java)
            Toast.makeText(this.context, "Bem vindo ${auth.currentUser!!.email}", Toast.LENGTH_LONG).show()
            startActivity(intt)
        }

        textoCadastroLogin.setOnClickListener{
            findNavController().navigate(R.id.LoginToCadastrar)
        }

        btnLogarLogin.setOnClickListener {
            usuarioViewModel.loginFirestore(requireContext().applicationContext,
                boxEmailLogin.text.toString(), boxSenhaLogin.text.toString())
        }
    }
}


package com.example.pb_android_radion.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
<<<<<<< HEAD:app/src/main/java/com.projeto.pb_android_radion/fragment/LoginFragment.kt
import com.projeto.pb_android_radion.R
import com.example.pb_android_radion.viewModel.UsuarioViewModel
=======
import com.example.pb_android_radion.MainActivity
import com.example.pb_android_radion.R

>>>>>>> parent of 6a6e5f8... Mudando search:app/src/main/java/com/example/pb_android_radion/fragment/LoginFragment.kt
import com.google.firebase.auth.FirebaseUser
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


        textoCadastroLogin.setOnClickListener{
            findNavController().navigate(R.id.LoginToCadastrar)
        }

        btnLogarLogin.setOnClickListener {
            usuarioViewModel.loginFirestore(requireContext().applicationContext,
                boxEmailLogin.text.toString(), boxSenhaLogin.text.toString())
<<<<<<< HEAD:app/src/main/java/com.projeto.pb_android_radion/fragment/LoginFragment.kt
//            if (usuarioViewModel.confirmaLogin()){
//                startActivity(Intent(activity?.baseContext, MainActivity::class.java))
//            }
=======
            if (usuarioViewModel.confirmaLogin()){
                startActivity(Intent(activity?.baseContext, MainActivity::class.java))
            }
>>>>>>> parent of 6a6e5f8... Mudando search:app/src/main/java/com/example/pb_android_radion/fragment/LoginFragment.kt
        }
    }
}


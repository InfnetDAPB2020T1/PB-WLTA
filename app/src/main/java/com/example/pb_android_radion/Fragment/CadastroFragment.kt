package com.example.pb_android_radion.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.pb_android_radion.Model.Usuario

import com.example.pb_android_radion.R
import com.example.pb_android_radion.ViewModel.UsuarioViewModel
import kotlinx.android.synthetic.main.fragment_cadastro.*


class CadastroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lateinit var usuarioViewModel: UsuarioViewModel
        activity?.let {
            usuarioViewModel = ViewModelProviders.of(it).get(UsuarioViewModel::class.java)


            var Usuario = Usuario(boxEmailCadastro.text.toString(),
                boxSenhaCadastro.text.toString(),
                boxNomeCadastro.text.toString(),
                boxSobrenomeCadastro.text.toString(),
                boxApelidoCadastro.text.toString(),
                boxEstadoCadastro.text.toString(),
                boxCpf.text.toString(),
                boxDDDCadastro.text.toString(),
                boxTelefoneCadastro.text.toString())

            usuarioViewModel.addUsuario(Usuario)

        }
    }


}

package com.example.pb_android_radion.fragment

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pb_android_radion.MainActivity
import com.example.pb_android_radion.model.Usuario
import com.example.pb_android_radion.R
import com.example.pb_android_radion.service.AppDatabaseService
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

        textoCadastroLogin.setOnClickListener{
            findNavController().navigate(R.id.LoginToCadastrar)
        }

        btnLogarLogin.setOnClickListener {
            OperacaoBancoTask().execute()
        }
    }

    inner class OperacaoBancoTask : AsyncTask<Unit, Unit, Array<Usuario>>(){

        override fun doInBackground(vararg params: Unit?): Array<Usuario> {
            var db = AppDatabaseService.getInstance(activity!!.baseContext)

            return db.usuarioDao().listarUsuarios()
        }

        override fun onPostExecute(result: Array<Usuario>?) {
            super.onPostExecute(result)

            result!!.forEach {
                if(boxEmailLogin.text.toString() == it.email &&
                    boxSenhaLogin.text.toString() == it.senha){
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("usuario", it)
                    startActivity(intent)
                }
            }
        }
    }
}

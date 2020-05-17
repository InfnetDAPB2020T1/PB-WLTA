package com.example.pb_android_radion.task

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.example.pb_android_radion.MainActivity
import com.example.pb_android_radion.model.Usuario
import com.example.pb_android_radion.service.AppDatabaseService
import com.example.pb_android_radion.viewModel.UsuarioViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_login.*

class OperacaoBancoLoginTask(
    private val applicationContext: Context,
    private val boxEmailLogin: TextInputEditText,
    private val boxSenhaLogin: TextInputEditText,
    private val usuarioViewModel: UsuarioViewModel
) : AsyncTask<Unit, Unit, Array<Usuario>>(){

    override fun doInBackground(vararg params: Unit?): Array<Usuario> {
        var db = AppDatabaseService.getInstance(applicationContext)

        return db.usuarioDao().listarUsuarios()
    }

    override fun onPostExecute(result: Array<Usuario>?) {
        super.onPostExecute(result)

        result!!.forEach {

            if(boxEmailLogin.text.toString() == it.email &&
                boxSenhaLogin.text.toString() == it.senha){

                usuarioViewModel.usuarioLogado

                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("it",it)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                applicationContext.startActivity(intent)

            }else{
                Toast.makeText(applicationContext, "Usuário inválido",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.example.pb_android_radion.task

import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.example.pb_android_radion.viewModel.UsuarioViewModel
import com.google.android.material.navigation.NavigationView

class SalvarUsuarioBancoTask(
    private val context: Context,
    private val usuarioViewModel: UsuarioViewModel
) : AsyncTask<Unit, Unit, Unit>() {

    override fun doInBackground(vararg params: Unit?) {
        //Toast.makeText(activity!!.baseContext, "Salvando seu cadastro", Toast.LENGTH_LONG)
        usuarioViewModel.salvarNoBanco(context)
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        Toast.makeText(context, "Cadastro salvo com sucesso", Toast.LENGTH_LONG)
    }
}
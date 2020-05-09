package com.example.pb_android_radion.fragment

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.pb_android_radion.model.Usuario

import com.example.pb_android_radion.R
import com.example.pb_android_radion.service.AppDatabaseService
import com.example.pb_android_radion.viewModel.UsuarioViewModel
import kotlinx.android.synthetic.main.fragment_complemento_cadastro.*

class ComplementoCadastroFragment : Fragment() {

    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complemento_cadastro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            usuarioViewModel = ViewModelProviders.of(it).get(UsuarioViewModel::class.java)
        }

        btnFinalizarCadastro.setOnClickListener{
            //Verifico se algum campo está nulo ou vazio
            verificarNulo()

            OperacaoBancoTask().execute()

            findNavController().navigate(R.id.returnToLogin)
        }
    }

    inner class OperacaoBancoTask : AsyncTask<Unit, Unit, Unit>(){

        override fun doInBackground(vararg params: Unit?) {
            //Toast.makeText(activity!!.baseContext, "Salvando seu cadastro", Toast.LENGTH_LONG)
            salvarNoBanco()
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            //Toast.makeText(activity!!.baseContext, "Cadastro salvo com sucesso", Toast.LENGTH_LONG)
        }
    }

    /*private fun complementarCadastro(){
        usuarioViewModel.complementoCadastro(boxNomeCadastro.text.toString(),
            boxSobrenomeCadastro.text.toString(), boxCpf.text.toString(),
            boxEstadoCadastro.text.toString(), boxDDDCadastro.text.toString(),
            boxTelefoneCadastro.text.toString())

    }*/

    private fun salvarNoBanco(){
        val novoUsuario = Usuario(
            usuarioViewModel.usuario!!.apelido,
            usuarioViewModel.usuario!!.email,
            usuarioViewModel.usuario!!.senha,
            usuarioViewModel.usuario!!.nome,
            usuarioViewModel.usuario!!.sobrenome,
            usuarioViewModel.usuario!!.cpf,
            usuarioViewModel.usuario!!.estado,
            usuarioViewModel.usuario!!.ddd,
            usuarioViewModel.usuario!!.telefone
        )

        var db = AppDatabaseService.getInstance(requireActivity().baseContext)

        db.usuarioDao().criarUsuario(novoUsuario)
    }

    private fun verificarNulo(){
        //Verifico se algum campo está nulo ou vazio
        if(boxNomeCadastro.text.isNullOrEmpty() || boxSobrenomeCadastro.text.isNullOrEmpty()
            || boxEstadoCadastro.text.isNullOrEmpty() || boxCpf.text.isNullOrEmpty()
            || boxDDDCadastro.text.isNullOrEmpty() || boxTelefoneCadastro.text.isNullOrEmpty()){

            Toast.makeText(requireActivity().baseContext, "Por favor preencha todos os campos",
                Toast.LENGTH_SHORT).show()
        }else {
            //Caso tudo ocorra ok, começo a alimentar o view model com o resto das informações
            usuarioViewModel.usuario!!.nome = boxNomeCadastro.text.toString()
            usuarioViewModel.usuario!!.sobrenome = boxSobrenomeCadastro.text.toString()
            usuarioViewModel.usuario!!.estado = boxEstadoCadastro.text.toString()
            usuarioViewModel.usuario!!.cpf = boxCpf.text.toString()
            usuarioViewModel.usuario!!.ddd = boxDDDCadastro.text.toString()
            usuarioViewModel.usuario!!.telefone = boxTelefoneCadastro.text.toString()
        }
    }
}

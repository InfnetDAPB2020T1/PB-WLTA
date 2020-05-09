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
import kotlinx.android.synthetic.main.layout_cadastro.*

/**
 * A simple [Fragment] subclass.
 */
class ComplementoCadastroFragment : Fragment() {

    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_cadastro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            usuarioViewModel = ViewModelProviders.of(it).get(UsuarioViewModel::class.java)
        }

        btnCadastrarUsuario.setOnClickListener{
            //Verifico se algum campo está nulo ou vazio
            verificarNulo()

            OperacaoBancoTask().execute()

            //Cria novo Usuário
            val novoUsuario = Usuario(
                usuarioViewModel.apelido.toString(),
                usuarioViewModel.email.toString(),
                usuarioViewModel.senha.toString(),
                usuarioViewModel.nome.toString(),
                usuarioViewModel.sobrenome.toString(),
                usuarioViewModel.cpf.toString(),
                usuarioViewModel.estado.toString(),
                usuarioViewModel.ddd.toString(),
                usuarioViewModel.telefone.toString()
            )

            usuarioViewModel.usuario == novoUsuario
            //add usuário na lista de Usuarios
            usuarioViewModel.listaUsuariosSeriazable?.lista?.add(novoUsuario)
            usuarioViewModel.usuarios.add(novoUsuario)

            findNavController().navigate(R.id.loginFragment)
        }
    }

    inner class OperacaoBancoTask : AsyncTask<Unit, Unit, Unit>(){

        override fun doInBackground(vararg params: Unit?) {
            //Toast.makeText(activity!!.baseContext, "Salvando seu cadastro", Toast.LENGTH_LONG)
            salvarNoBanco()
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            Toast.makeText(activity?.baseContext, "Cadastro salvo com sucesso", Toast.LENGTH_LONG)
        }
    }

    private fun complementarCadastro(){
        usuarioViewModel.complementoCadastro(boxNomeCadastro.text.toString(),
            boxSobrenomeCadastro.text.toString(), boxCpf.text.toString(),
            boxEstadoCadastro.text.toString(), boxDDDCadastro.text.toString(),
            boxTelefoneCadastro.text.toString())

    }

    private fun salvarNoBanco(){
        val novoUsuario = Usuario(
            usuarioViewModel.apelido!!,
            usuarioViewModel.email!!,
            usuarioViewModel.senha!!,
            usuarioViewModel.nome!!,
            usuarioViewModel.sobrenome!!,
            usuarioViewModel.cpf!!,
            usuarioViewModel.estado!!,
            usuarioViewModel.ddd!!,
            usuarioViewModel.telefone!!
        )

        /*Log.i("Usuario", "${usuarioViewModel.apelido}, ${usuarioViewModel.email}," +
                "${usuarioViewModel.senha}, ${usuarioViewModel.nome}, ${usuarioViewModel.sobrenome}," +
                "${usuarioViewModel.cpf}, ${usuarioViewModel.estado}, ${usuarioViewModel.ddd}, " +
                "${usuarioViewModel.telefone}")*/

        var db = AppDatabaseService.getInstance(requireContext().applicationContext)

        db.usuarioDao().criarUsuario(novoUsuario)

    }

    private fun verificarNulo(){
        //Verifico se algum campo está nulo ou vazio
        if(boxNomeCadastro.text.isNullOrEmpty() || boxSobrenomeCadastro.text.isNullOrEmpty() ||
            boxApelidoCadastro.text.isNullOrEmpty() || boxEmailCadastro.text.isNullOrEmpty()
            || boxEstadoCadastro.text.isNullOrEmpty() || boxCpf.text.isNullOrEmpty()
            || boxDDDCadastro.text.isNullOrEmpty() || boxTelefoneCadastro.text.isNullOrEmpty()){

            Toast.makeText(activity?.baseContext, "Por favor preencha todos os campos",
                Toast.LENGTH_SHORT).show()
        }else {
            //Caso tudo ocorra ok, começo a alimentar o view model com o resto das informações
            usuarioViewModel.nome = boxNomeCadastro.text.toString()
            usuarioViewModel.sobrenome = boxSobrenomeCadastro.text.toString()
            usuarioViewModel.apelido = boxApelidoCadastro.text.toString()
            usuarioViewModel.email = boxEmailCadastro.text.toString()
            usuarioViewModel.estado = boxEstadoCadastro.text.toString()
            usuarioViewModel.cpf = boxCpf.text.toString()
            usuarioViewModel.ddd = boxDDDCadastro.text.toString()
            usuarioViewModel.telefone = boxTelefoneCadastro.text.toString()
        }
    }
}

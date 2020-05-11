package com.example.pb_android_radion.fragment

import android.content.Intent
import android.os.AsyncTask
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
import com.example.pb_android_radion.model.Usuario
import com.example.pb_android_radion.R
import com.example.pb_android_radion.service.AppDatabaseService
import com.example.pb_android_radion.viewModel.UsuarioViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.layout_cadastro.*

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

        //val listaUsuarios = usuarioViewModel.listaUsuariosSeriazable
        btnLogarLogin.setOnClickListener {
            OperacaoBancoTask().execute()

            //Aqui é pra testar sem precisar criar login toda hora
            //Para user este comente a linha 54 até a 66
//            val userTeste = Usuario("ApelidoAdmin","admin@email.com","a",
//            "NomeAdmin","SobrenomeAdmin","1111111111","XX",
//            "XX","XXXXXXXXXX")
//
//            val intent = Intent(context, MainActivity::class.java)
//            intent.putExtra("usuario", userTeste)
//            startActivity(intent)
            //Variavel para ver se o usuário digitado na tela existe ao percorrer a lista de Usuários
            //Para usar aqui comente da linha 45 até 51
            /*var existe: Boolean = false
            listaUsuarios!!.lista.forEach{
                if(boxEmailLogin.text.toString().compareTo(it.email) == && boxSenhaLogin.text.toString() == it.senha){
                    existe = true
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("usuario", it)
                    intent.putExtra("listaUsuarios", listaUsuarios)
                    startActivity(intent)
                }
            }
            if(!existe){
                Toast.makeText(this.context, "Usuário inválido", Toast.LENGTH_SHORT).show()
            }*/
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

                    //preenchendo o objeto usuarioLogado
                  /*  usuarioViewModel.usuarioLogado = Usuario(
                        apelido = it.apelido,
                        imagem = it.imagem,
                        email = it.email,
                        senha = it.senha,
                        nome = it.nome,
                        cpf = it.cpf,
                        estado = it.estado,
                        ddd = it.ddd,
                        telefone = it.telefone
                    )*/

                            usuarioViewModel.usuarioLogado

                    /*usuarioViewModel.usuarioLogado!!.nome = it.nome
                    usuarioViewModel.usuarioLogado!!.apelido = it.apelido
                    usuarioViewModel.usuarioLogado!!.email = it.email
                    usuarioViewModel.usuarioLogado!!.senha = it.senha
                    usuarioViewModel.usuarioLogado!!.cpf = it.cpf
                    usuarioViewModel.usuarioLogado!!.telefone = it.telefone
                    usuarioViewModel.usuarioLogado!!.ddd = it.ddd
                    usuarioViewModel.usuarioLogado!!.estado = it.estado
*/
                    //usuarioViewModel.usuarioLogado = it --> salva o endereço de memoria do objeto

                    /*Log.i("Usuario", "${it.email}," +
                            "${it.senha}," +
                            "${it.apelido}," +
                            "${it.nome}," +
                            "${it.cpf}," +
                            "${it.estado}," +
                            "${it.ddd}," +
                            "${it.telefone}")*/

                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("it",it)
                    startActivity(intent)

                }else{
                    Toast.makeText(activity!!.baseContext, "Usuário inválido",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

package com.example.pb_android_radion.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.pb_android_radion.model.Usuario

import com.example.pb_android_radion.R
import com.example.pb_android_radion.service.AppDatabaseService
import com.example.pb_android_radion.viewModel.UsuarioViewModel
import kotlinx.android.synthetic.main.fragment_cadastro.*
import kotlinx.android.synthetic.main.fragment_cadastro.view.*
import kotlinx.android.synthetic.main.fragment_cadastro.view.boxSenhaCadastro
import kotlinx.android.synthetic.main.layout_cadastro.*
import kotlinx.android.synthetic.main.layout_cadastro.boxApelidoCadastro
import kotlinx.android.synthetic.main.layout_cadastro.boxEmailCadastro
import kotlin.math.log

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

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            usuarioViewModel = ViewModelProviders.of(it).get(UsuarioViewModel::class.java)
        }
        fotoPerfil()

        btnCadastrarUsuario.setOnClickListener {
            //Verifico se algum campo está nulo ou vazio
            verificarNulo()
            OperacaoBancoTask().execute()
        }

    }

    @SuppressLint("WrongConstant")
    fun fotoPerfil(){
        imageViewIncluirFotoPerfil.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(requireContext().applicationContext,Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                } else {
                    //permission already granted
                    //pickImageFromGallery()
                    FotoTask().execute()
                }
            } else {
                //system OS is < Marshmallow
                //pickImageFromGallery()
                FotoTask().execute()
            }
        }
    }
    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }
    fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    //permissao
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                   // FotoTask().execute()
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(activity?.baseContext, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //resultado da busca pela imagem
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageViewPerfil.setImageURI(data?.data)

        }
    }
    inner class FotoTask : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg params: Unit?) {
            //Toast.makeText(activity!!.baseContext, "Salvando seu cadastro", Toast.LENGTH_LONG)
            pickImageFromGallery()
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            Toast.makeText(activity?.baseContext, "Cadastro salvo com sucesso", Toast.LENGTH_LONG)
        }
    }
    inner class OperacaoBancoTask : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg params: Unit?) {
            //Toast.makeText(activity!!.baseContext, "Salvando seu cadastro", Toast.LENGTH_LONG)
            salvarNoBanco()
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            Toast.makeText(activity?.baseContext, "Cadastro salvo com sucesso", Toast.LENGTH_LONG)
        }
    }
/*
    private fun complementarCadastro() {
        usuarioViewModel.complementoCadastro(
            boxNomeCadastro.text.toString(),
           // boxSobrenomeCadastro.text.toString(),
            boxCpf.text.toString(),
            boxEstadoCadastro.text.toString(), boxDDDCadastro.text.toString(),
            boxTelefoneCadastro.text.toString()
        )
    }*/

    private fun salvarNoBanco() {

        val novoUsuario = Usuario(
            usuarioViewModel.usuario!!.apelido,
            usuarioViewModel.usuario!!.imagem,
            usuarioViewModel.usuario!!.email,
            usuarioViewModel.usuario!!.senha,
            usuarioViewModel.usuario!!.nome,
            usuarioViewModel.usuario!!.cpf,
            usuarioViewModel.usuario!!.estado,
            usuarioViewModel.usuario!!.ddd,
            usuarioViewModel.usuario!!.telefone
        )

        var db = AppDatabaseService.getInstance(requireContext().applicationContext)

        db.usuarioDao().criarUsuario(novoUsuario)
        findNavController().navigate(R.id.loginFragment)
    }

    private fun verificarNulo() {
        //Verifico se algum campo está nulo ou vazio
        if (boxNomeCadastro.text.isNullOrEmpty() ||
            boxApelidoCadastro.text.isNullOrEmpty() || boxEmailCadastro.text.isNullOrEmpty()
            || boxEstadoCadastro.text.isNullOrEmpty() || boxCpf.text.isNullOrEmpty()
            || boxDDDCadastro.text.isNullOrEmpty() || boxTelefoneCadastro.text.isNullOrEmpty()
        ) {
            Toast.makeText(
                activity?.baseContext, "Por favor preencha todos os campos",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            print(boxApelidoCadastro.text.toString())
            //Caso tudo ocorra ok, começo a alimentar o view model com o resto das informações
            usuarioViewModel.usuario = Usuario(
                apelido = boxApelidoCadastro.text.toString(),
                imagem = imageViewPerfil.toString(),
                email = boxEmailCadastro.text.toString(),
                senha = boxSenhaCadastro2.text.toString(),
                nome = boxNomeCadastro.text.toString(),
                cpf = boxCpf.text.toString(),
                estado = boxEstadoCadastro.text.toString(),
                ddd = boxDDDCadastro.text.toString(),
                telefone = boxTelefoneCadastro.text.toString()
            )
        }
    }
}


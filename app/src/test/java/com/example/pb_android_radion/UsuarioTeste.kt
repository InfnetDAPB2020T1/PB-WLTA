package com.example.pb_android_radion

import com.example.pb_android_radion.Model.Usuario
import com.example.pb_android_radion.Throws.UsuarioCPFException
import com.example.pb_android_radion.Throws.UsuarioDDDException
import com.example.pb_android_radion.Throws.UsuarioTelefoneException
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before


class UsuarioTeste {

    lateinit var usuario: Usuario

    @Before
    fun setupUsuario(){
        usuario = Usuario("Apelido", "Email", "Senha", "Nome",
            "Sobrenome", "11111111111", "Estado", "99","999999999")
    }

    @Test
    fun verifica_usuario_criado_com_sucesso(){
        assertTrue(usuario is Usuario)
    }

    @Test
    fun verifica_DDD_com_dois_digitos(){
        try {
            usuario.ddd = "999"
        }catch (e: UsuarioDDDException){
            assertEquals(UsuarioDDDException().message, e.message)
        }
    }

    @Test
    fun verifica_telefone_com_nove_digitos(){
        try {
            usuario.telefone = "555"
        }catch (e: UsuarioTelefoneException){
            assertEquals(UsuarioTelefoneException().message, e.message)
        }
    }

    @Test
    fun verifica_CPF_com_onze_digitos(){
        try {
            usuario.cpf = "4444"
        }catch (e: UsuarioCPFException){
            assertEquals(UsuarioCPFException().message, e.message)
        }
    }
}
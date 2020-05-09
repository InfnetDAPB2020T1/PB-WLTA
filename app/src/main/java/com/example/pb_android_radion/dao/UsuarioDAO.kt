package com.example.pb_android_radion.dao

import androidx.room.*
import com.example.pb_android_radion.model.Usuario

@Dao
interface UsuarioDAO {

    @Insert
    fun criarUsuario(usuario: Usuario)

    @Delete
    fun deleteUsuario(usuario: Usuario)

    @Update
    fun atualizarUsuario(usuario: Usuario)

    @Query("UPDATE usuarios SET apelido = :apelido Where id = :id")
    fun mudarApelido(apelido: String, id: Int?)

    @Query("UPDATE usuarios SET estado = :estado WHERE id =:id")
    fun mudarEstado(estado: String, id: Int?)

    @Query("UPDATE usuarios SET senha =:senha WHERE id =:id")
    fun mudarSenha(senha: String, id: Int?)

    @Query("SELECT * FROM usuarios")
    fun listarUsuarios(): Array<Usuario>

    @Query("SELECT * FROM usuarios WHERE nome = :nome")
    fun procurarUsuarioPeloId(nome: String) : Array<Usuario>

    @Query("DELETE FROM usuarios WHERE id = :id")
    fun deletarUsuarioPeloId(id: Int)
}
package com.example.pb_android_radion.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pb_android_radion.dao.UsuarioDAO
import com.example.pb_android_radion.model.Usuario

@Database(
    entities = [Usuario::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun usuarioDao() : UsuarioDAO
}
package com.example.pb_android_radion.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pb_android_radion.Dao.UsuarioDAO
import com.example.pb_android_radion.Model.Usuario

@Database(
    entities = [Usuario::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun usuarioDao() : UsuarioDAO
}
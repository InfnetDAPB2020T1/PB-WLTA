package com.example.pb_android_radion.Service

import android.content.Context
import androidx.room.Room
import com.example.pb_android_radion.Database.AppDatabase

class AppDatabaseService {

    companion object{
        private var instance: AppDatabase? = null
        private val databaseName = "appDatabase.sql"
        fun getInstance(context: Context): AppDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    databaseName
                ).build()
            }
            return instance as AppDatabase
        }
    }
}
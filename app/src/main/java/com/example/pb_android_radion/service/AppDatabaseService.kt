package com.example.pb_android_radion.service

import android.content.Context
import androidx.room.Room
import com.example.pb_android_radion.database.AppDatabase

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
                ).allowMainThreadQueries().build()
            }
            return instance as AppDatabase
        }
    }
}
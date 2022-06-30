package com.example.appmatchservice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appmatchservice.dao.ServicoDAO
import com.example.appmatchservice.dao.UsuarioDAO
import com.example.appmatchservice.enity.ServicoEntidade
import com.example.appmatchservice.enity.UsuarioEntidade

@Database(
    version = 1,
    entities =  [
        UsuarioEntidade::class,
        ServicoEntidade::class
    ]
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getUsuarioDAO(): UsuarioDAO
    abstract fun getServicoDAO(): ServicoDAO

    companion object {

        private var instance : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database_db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }


}
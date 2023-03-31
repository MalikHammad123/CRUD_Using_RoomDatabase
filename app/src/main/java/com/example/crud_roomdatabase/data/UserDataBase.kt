package com.example.crud_roomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version =1, exportSchema = false)
abstract class UserDataBase :RoomDatabase(){
    abstract fun getUserDao():UserDao


    companion object{
        @Volatile
        private var instance:UserDataBase?=null
        private val LOCK=Any()

        operator fun invoke(context: Context)=
            instance?: synchronized(LOCK){
            instance?:buildDatabase(context).also{
                instance=it
            }
        }
        private fun buildDatabase(context: Context)= Room.databaseBuilder(
                context.applicationContext,
            UserDataBase::class.java,
            "app-database"
        ).build()

    }
}
package com.example.roomdatabaseknowledge.roomobject

import android.content.Context

object SingletonDatabase {
    var database: StudentRoomDatabase? = null

    fun getDatabase(context: Context): StudentRoomDatabase{
        if(database == null)
            database = StudentRoomDatabase.getDatabase(context)
        return database!!
    }
}
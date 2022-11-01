package com.example.prototipo_tea_1.model.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prototipo_tea_1.model.data.database.dao.RutinaDao
import com.example.prototipo_tea_1.model.data.database.entities.Rutina

@Database (
    entities = [Rutina::class],
    version = 1,
    exportSchema = false)

abstract class RutinaDatabase: RoomDatabase() {

    //variable del dao
    abstract fun rutinaDao():RutinaDao

    companion object{
        @Volatile
        private var INSTANCE: RutinaDatabase? = null

        //Se crea la bd
        fun getDatabase(context: Context): RutinaDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RutinaDatabase::class.java,
                    "rutinaDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }



    ////Creacion del room
    //    val room: RutinaDatabase = Room
    //        .databaseBuilder(this, RutinaDatabase::class.java, "rutina")
    //        .build()
    //val rutinas = room.rutinaDao().getAll()
}
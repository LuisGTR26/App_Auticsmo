package com.example.prototipo_tea_1.model.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prototipo_tea_1.model.data.database.dao.RutinaDao
import com.example.prototipo_tea_1.model.data.database.entities.AmbitoEntities
import com.example.prototipo_tea_1.model.data.database.entities.RutinaEntities

@Database (entities = [RutinaEntities::class], version = 1)
abstract class RutinaDatabase: RoomDatabase() {

    abstract fun rutinaDao():RutinaDao

    //Creacion del room
    val room: RutinaDatabase = Room
        .databaseBuilder(this, RutinaDatabase::class.java, "rutina")
        .build()

    //val rutinas = room.rutinaDao().getAll()
}
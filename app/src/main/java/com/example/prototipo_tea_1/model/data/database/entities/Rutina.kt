package com.example.prototipo_tea_1.model.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rutina_table")
data class Rutina(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idRutina" )val idRutina:Int = 0,
    @ColumnInfo(name = "titleRutina" )val titleRutina:String,
    @ColumnInfo(name = "ambito")val ambito:String
    )
    //@ColumnInfo(name = "imgRutina")val imgRutina:String,
    //@ColumnInfo(name = "titleAmbito")val titleAmbito:String )
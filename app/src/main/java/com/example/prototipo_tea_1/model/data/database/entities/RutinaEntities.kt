package com.example.prototipo_tea_1.model.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rutina_table")
data class RutinaEntities(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id" )val id:Int = 0,
    @ColumnInfo(name = "titulo" )val titulo:String,
    @ColumnInfo(name = "descripcion")val description:String,
    @ColumnInfo(name = "ambito")val ambito:String,
    @ColumnInfo(name = "img")val img:String  )
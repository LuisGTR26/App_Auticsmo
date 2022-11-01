package com.example.prototipo_tea_1.model.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "procedimiento_table")
data class Procedimiento (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idProcedimiento" )val idProcedimiento:Int = 0,
    @ColumnInfo(name = "titleProcedimiento" )val titleProcedimiento:String,
    @ColumnInfo(name = "descripcion")val description:String,
    @ColumnInfo(name = "imgProcedimiento")val imgProcedimiento:String,
    @ColumnInfo(name = "idRutina")val idRutina:String )

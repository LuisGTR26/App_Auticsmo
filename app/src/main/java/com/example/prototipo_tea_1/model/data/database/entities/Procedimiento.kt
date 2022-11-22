package com.example.prototipo_tea_1.model.data.database.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "procedimiento_table")
data class Procedimiento (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idProcedimiento" )val idProcedimiento:Int = 0,
    @ColumnInfo(name = "titleProcedimiento" )val titleProcedimiento:String,
    @ColumnInfo(name = "descripcion")val description:String,
    @ColumnInfo(name = "imgProcedimiento")val imgProcedimiento:Bitmap,
    @ColumnInfo(name = "rutina")val rutina:String
)


/*
* , foreignKeys = [
    ForeignKey(
        entity = Rutina::class,
        parentColumns = ["idRutina"],
        childColumns = ["rutinaId"]
    )]
    *
    * @ColumnInfo(name = "rutinaId")val rutinaId:Int
* */